package org.ss.simpleflow.core.impl.processengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.constant.SfProcessConfigIndexConstant;
import org.ss.simpleflow.core.context.*;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.factory.SfContextFactory;
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

        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> implements SfProcessValidateAndPreprocess<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> {

    private static final Logger LOG = LoggerFactory.getLogger(SfDefaultProcessValidateAndPreprocess.class);
    private final SfProcessEngineConfig processEngineConfig;
    private final SfValidateManager<NI, EI, PCI, NC, EC, PC> validateManager;
    private final SfContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> contextFactory;

    SfDefaultProcessValidateAndPreprocess(SfProcessEngineConfig processEngineConfig,
                                          SfValidateManager<NI, EI, PCI, NC, EC, PC> validateManager,
                                          SfContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> contextFactory) {
        this.processEngineConfig = processEngineConfig;
        this.validateManager = validateManager;
        this.contextFactory = contextFactory;
    }

    @Override
    public SfExecutionWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>
    validateAndPreprocess(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig) {

        if (wholeProcessConfig == null) {
            throw new NullPointerException("wholeProcessConfig can not be null");
        }
        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        if (mainProcessConfig == null) {
            throw new NullPointerException("mainProcessConfig can not be null");
        }


        SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext = contextFactory.createProcessContext();
        processContext.setProcessConfig(mainProcessConfig);


        SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationGlobalContext =
                createValidationWholeContext(wholeProcessConfig, contextFactory);
        validateManager.validate(wholeProcessConfig, validationGlobalContext, processEngineConfig);

        SfExecutionWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> executionGlobalContext =
                createExecutionGlobalContext(wholeProcessConfig,
                                             processContext,
                                             validationGlobalContext,
                                             contextFactory);
        return executionGlobalContext;
    }

    public SfValidationWholeContext<NI, EI, PCI, NC, EC, PC>
    createValidationWholeContext(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                                 SfContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> contextFactory) {
        SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext = contextFactory.createValidationWholeContext();
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext = contextFactory.createProcessValidationContext();
        validationWholeContext.setMainProcessValidationContext(mainProcessValidationContext);

        List<PC> subProcessConfigList = wholeProcessConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            int size = subProcessConfigList.size();
            List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>> subExecutionProcessContextList = new ArrayList<>(
                    size);
            validationWholeContext.setSubValidationProcessContextList(subExecutionProcessContextList);
            for (int i = 0; i < size; i++) {
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> processValidationContext = contextFactory.createProcessValidationContext();
                subExecutionProcessContextList.add(processValidationContext);
            }
        }
        return validationWholeContext;
    }

    private SfExecutionWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> createExecutionGlobalContext(
            SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
            SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext,
            SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationGlobalContext,
            SfContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> contextFactory) {
        SfExecutionWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> executionGlobalContext = contextFactory.createExecutionGlobalContext();

        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext = validationGlobalContext.getMainProcessValidationContext();
        SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainExecutionProcessContext = contextFactory.createExecutionProcessContext();
        executionGlobalContext.setMainExecutionProcessContext(mainExecutionProcessContext);
        SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainExecutionProcessInternalContext = contextFactory.createExecutionProcessInternalContext();
        mainExecutionProcessInternalContext.setProcessContext(processContext);
        mainExecutionProcessInternalContext.setProcessConfigIndex(SfProcessConfigIndexConstant.MAIN_PROCESS_CONFIG_INDEX);
        mainExecutionProcessContext.setExecutionInternalContext(mainExecutionProcessInternalContext);
        SfExecutionProcessExternalContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainExecutionProcessExternalContext = contextFactory.createExecutionProcessExternalContext();
        mainExecutionProcessContext.setExecutionExternalContext(mainExecutionProcessExternalContext);

        List<PC> subProcessConfigList = wholeProcessConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            int size = subProcessConfigList.size();
            List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> subExecutionProcessContextList = new ArrayList<>(
                    size);
            executionGlobalContext.setSubExecutionProcessContextList(subExecutionProcessContextList);
            for (int i = 0; i < size; i++) {
                SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> subExecutionProcessContext = contextFactory.createExecutionProcessContext();
                subExecutionProcessContextList.add(subExecutionProcessContext);

                subExecutionProcessContext.setExecutionInternalContext(contextFactory.createExecutionProcessInternalContext());
                subExecutionProcessContext.setExecutionExternalContext(contextFactory.createExecutionProcessExternalContext());
            }
        }
        return executionGlobalContext;
    }

}