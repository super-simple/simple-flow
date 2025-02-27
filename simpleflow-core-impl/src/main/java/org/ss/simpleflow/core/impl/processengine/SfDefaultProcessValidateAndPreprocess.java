package org.ss.simpleflow.core.impl.processengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.constant.SfProcessConfigIndexConstant;
import org.ss.simpleflow.core.context.SfProcessPreprocessData;
import org.ss.simpleflow.core.context.SfValidationProcessContext;
import org.ss.simpleflow.core.context.SfValidationWholeContext;
import org.ss.simpleflow.core.context.SfWholePreprocessData;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.factory.SfProcessValidateAndPreprocessDataFactory;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessValidateAndPreprocess;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.ArrayList;
import java.util.List;

public class SfDefaultProcessValidateAndPreprocess<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>>
        implements SfProcessValidateAndPreprocess<NI, EI, PCI, NC, EC, PC> {

    private static final Logger LOG = LoggerFactory.getLogger(SfDefaultProcessValidateAndPreprocess.class);
    private final SfProcessEngineConfig processEngineConfig;
    private final SfValidateManager<NI, EI, PCI, NC, EC, PC> validateManager;
    private final SfProcessValidateAndPreprocessDataFactory<NI, EI, PCI, NC, EC, PC> dataFactory;

    SfDefaultProcessValidateAndPreprocess(SfProcessEngineConfig processEngineConfig,
                                          SfValidateManager<NI, EI, PCI, NC, EC, PC> validateManager,
                                          SfProcessValidateAndPreprocessDataFactory<NI, EI, PCI, NC, EC, PC> dataFactory) {
        this.processEngineConfig = processEngineConfig;
        this.validateManager = validateManager;
        this.dataFactory = dataFactory;
    }

    @Override
    public SfWholePreprocessData<NI, EI, PCI, NC, EC, PC>
    validateAndPreprocess(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig) {

        if (wholeProcessConfig == null) {
            throw new NullPointerException("wholeProcessConfig can not be null");
        }
        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        if (mainProcessConfig == null) {
            throw new NullPointerException("mainProcessConfig can not be null");
        }


        SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationGlobalContext =
                createValidationWholeContext(wholeProcessConfig, dataFactory);
        validateManager.validate(wholeProcessConfig, validationGlobalContext, processEngineConfig);

        SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> executionGlobalContext =
                createExecutionGlobalContext(wholeProcessConfig,
                                             validationGlobalContext,
                                             dataFactory);
        return executionGlobalContext;
    }

    public SfValidationWholeContext<NI, EI, PCI, NC, EC, PC>
    createValidationWholeContext(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                                 SfProcessValidateAndPreprocessDataFactory<NI, EI, PCI, NC, EC, PC> dataFactory) {
        SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext = dataFactory.createValidationWholeContext();
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext = dataFactory.createValidationProcessContext();
        validationWholeContext.setMainProcessValidationContext(mainProcessValidationContext);

        List<PC> subProcessConfigList = wholeProcessConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            int size = subProcessConfigList.size();
            List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>> subExecutionProcessContextList = new ArrayList<>(
                    size);
            validationWholeContext.setSubValidationProcessContextList(subExecutionProcessContextList);
            for (int i = 0; i < size; i++) {
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> processValidationContext = dataFactory.createValidationProcessContext();
                subExecutionProcessContextList.add(processValidationContext);
            }
        }
        return validationWholeContext;
    }

    private SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> createExecutionGlobalContext(
            SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
            SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationGlobalContext,
            SfProcessValidateAndPreprocessDataFactory<NI, EI, PCI, NC, EC, PC> dataFactory) {
        SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> executionGlobalContext = dataFactory.createWholePreprocessData();

        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext = validationGlobalContext.getMainProcessValidationContext();
        SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainExecutionProcessContext = dataFactory.createProcessPreprocessData();
        executionGlobalContext.setMainExecutionProcessContext(mainExecutionProcessContext);
        mainExecutionProcessContext.setProcessConfigIndex(SfProcessConfigIndexConstant.MAIN_PROCESS_CONFIG_INDEX);

        List<PC> subProcessConfigList = wholeProcessConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            int size = subProcessConfigList.size();
            List<SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>> subExecutionProcessContextList = new ArrayList<>(
                    size);
            executionGlobalContext.setSubExecutionProcessContextList(subExecutionProcessContextList);
            for (int i = 0; i < size; i++) {
                SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> subExecutionProcessContext = dataFactory.createProcessPreprocessData();
                subExecutionProcessContextList.add(subExecutionProcessContext);
            }
        }
        return executionGlobalContext;
    }

}