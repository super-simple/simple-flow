package org.ss.simpleflow.core.impl.processengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            throw new IllegalArgumentException("wholeProcessConfig can not be null");
        }
        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        if (mainProcessConfig == null) {
            throw new IllegalArgumentException("mainProcessConfig can not be null");
        }

        PC[] subProcessConfigArray = wholeProcessConfig.getSubProcessConfigArray();
        if (subProcessConfigArray == null) {
            throw new IllegalArgumentException("subProcessConfigArray can not be null (but can be empty)");
        }

        SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext =
                createValidationWholeContext(wholeProcessConfig, dataFactory);

        validateManager.validate(wholeProcessConfig, validationWholeContext, processEngineConfig);

        return createExecutionGlobalContext(wholeProcessConfig, validationWholeContext, dataFactory);
    }

    public SfValidationWholeContext<NI, EI, PCI, NC, EC, PC>
    createValidationWholeContext(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                                 SfProcessValidateAndPreprocessDataFactory<NI, EI, PCI, NC, EC, PC> dataFactory) {
        SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext = dataFactory.createValidationWholeContext();
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext = dataFactory.createValidationProcessContext();
        validationWholeContext.setMainValidationProcessContext(mainProcessValidationContext);

        PC[] subProcessConfigArray = wholeProcessConfig.getSubProcessConfigArray();
        int length = subProcessConfigArray.length;
        @SuppressWarnings("unchecked")
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] subExecutionProcessContextArray = new SfValidationProcessContext[length];
        validationWholeContext.setSubValidationProcessContextArray(subExecutionProcessContextArray);
        for (int i = 0; i < length; i++) {
            subExecutionProcessContextArray[i] = dataFactory.createValidationProcessContext();
        }
        return validationWholeContext;
    }

    private SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> createExecutionGlobalContext(
            SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
            SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext,
            SfProcessValidateAndPreprocessDataFactory<NI, EI, PCI, NC, EC, PC> dataFactory) {
        SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData = dataFactory.createWholePreprocessData();

        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext = validationWholeContext.getMainValidationProcessContext();
        SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainExecutionProcessContext = dataFactory.createProcessPreprocessData();
        assignProcessPreprocessData(mainProcessValidationContext, mainExecutionProcessContext);
        wholePreprocessData.setMainProcessPreprocessData(mainExecutionProcessContext);
        mainExecutionProcessContext.setProcessConfigIndex(SfProcessConfigIndexConstant.MAIN_PROCESS_CONFIG_INDEX);

        PC[] subProcessConfigArray = wholeProcessConfig.getSubProcessConfigArray();
        int length = subProcessConfigArray.length;
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] subValidationProcessContextArray = validationWholeContext.getSubValidationProcessContextArray();
        @SuppressWarnings("unchecked")
        SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>[] subExecutionProcessContextArray = new SfProcessPreprocessData[length];
        wholePreprocessData.setSubProcessPreprocessDataArray(subExecutionProcessContextArray);
        for (int i = 0; i < length; i++) {
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> subValidationProcessContext = subValidationProcessContextArray[i];
            SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> subExecutionProcessContext = dataFactory.createProcessPreprocessData();
            assignProcessPreprocessData(subValidationProcessContext, subExecutionProcessContext);
            subExecutionProcessContextArray[i] = subExecutionProcessContext;
        }
        return wholePreprocessData;
    }

    private void assignProcessPreprocessData(SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> validationProcessContext,
                                             SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> processPreprocessData) {
        processPreprocessData.setStartNodeConfigIndex(validationProcessContext.getStartNodeConfigIndex());
    }

}