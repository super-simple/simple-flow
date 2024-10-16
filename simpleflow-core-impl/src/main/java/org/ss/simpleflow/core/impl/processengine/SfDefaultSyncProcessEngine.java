package org.ss.simpleflow.core.impl.processengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.aspect.SfEdgeAspect;
import org.ss.simpleflow.core.aspect.SfNodeAspect;
import org.ss.simpleflow.core.aspect.SfProcessAspect;
import org.ss.simpleflow.core.constant.SfProcessConfigIndexConstant;
import org.ss.simpleflow.core.context.*;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.factory.*;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfComponentExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfSyncProcessEngine;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SfDefaultSyncProcessEngine<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI>
        implements SfSyncProcessEngine<NI, EI, PCI, NC, EC, PCG,
        PC, NEI, EEI, PEI> {

    private static final Logger LOG = LoggerFactory.getLogger(SfDefaultSyncProcessEngine.class);

    private final SfProcessEngineConfig processEngineConfig;

    private final SfControlEdgeFactory<NI, EI, PCI, NC, EC, PCG, PC, EEI, PEI> controlEdgeFactory;
    private final SfDataEdgeFactory<NI, EI, PCI, NC, EC, PCG, PC, EEI, PEI> dataEdgeFactory;
    private final SfEventFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> eventFactory;
    private final SfNodeFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> nodeFactory;
    private final SfEnumGatewayFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> enumGatewayFactory;
    private final SfStreamIteratorFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> streamIteratorFactory;
    private final SfGatewayFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> gatewayFactory;
    private final SfAroundIteratorFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> aroundIteratorFactory;

    private final SfValidateManager<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validateManager;

    private final SfComponentExecutionIdGenerator<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> componentExecutionIdGenerator;
    private final SfProcessExecutionIdGenerator<NI, EI, PCI, NC, EC, PCG, PC, PEI> processExecutionIdGenerator;

    private final SfContextFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> contextFactory;

    private final List<SfEdgeAspect<NI, EI, PCI, NC, EC, PCG, PC, EEI, PEI>> edgeAspectList;
    private final List<SfNodeAspect<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI>> nodeAspectList;
    private final List<SfProcessAspect<NI, EI, PCI, NC, EC, PCG, PC, PEI>> processAspectList;

    SfDefaultSyncProcessEngine(SfProcessEngineConfig processEngineConfig,
                               SfControlEdgeFactory<NI, EI, PCI, NC, EC, PCG, PC, EEI, PEI> controlEdgeFactory,
                               SfDataEdgeFactory<NI, EI, PCI, NC, EC, PCG, PC, EEI, PEI> dataEdgeFactory,
                               SfEventFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> eventFactory,
                               SfNodeFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> nodeFactory,
                               SfEnumGatewayFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> enumGatewayFactory,
                               SfStreamIteratorFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> streamIteratorFactory,
                               SfGatewayFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> gatewayFactory,
                               SfAroundIteratorFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> aroundIteratorFactory,
                               SfValidateManager<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validateManager,
                               SfComponentExecutionIdGenerator<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> componentExecutionIdGenerator,
                               SfProcessExecutionIdGenerator<NI, EI, PCI, NC, EC, PCG, PC, PEI> processExecutionIdGenerator,
                               SfContextFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> contextFactory,
                               List<SfEdgeAspect<NI, EI, PCI, NC, EC, PCG, PC, EEI, PEI>> edgeAspectList,
                               List<SfNodeAspect<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI>> nodeAspectList,
                               List<SfProcessAspect<NI, EI, PCI, NC, EC, PCG, PC, PEI>> processAspectList) {
        if (processEngineConfig == null) {
            throw new IllegalArgumentException("SfProcessEngineConfig can not be null");
        }
        this.processEngineConfig = processEngineConfig;

        if (controlEdgeFactory == null) {
            throw new IllegalArgumentException("SfControlEdgeFactory can not be null");
        }
        this.controlEdgeFactory = controlEdgeFactory;

        if (dataEdgeFactory == null) {
            throw new IllegalArgumentException("SfDataEdgeFactory can not be null");
        }
        this.dataEdgeFactory = dataEdgeFactory;

        if (eventFactory == null) {
            throw new IllegalArgumentException("SfEventFactory can not be null");
        }
        this.eventFactory = eventFactory;

        if (nodeFactory == null) {
            throw new IllegalArgumentException("SfNodeFactory can not be null");
        }
        this.nodeFactory = nodeFactory;

        if (enumGatewayFactory == null) {
            throw new IllegalArgumentException("SfEnumGatewayFactory can not be null");
        }
        this.enumGatewayFactory = enumGatewayFactory;

        if (streamIteratorFactory == null) {
            throw new IllegalArgumentException("SfStreamIteratorFactory can not be null");
        }
        this.streamIteratorFactory = streamIteratorFactory;

        if (gatewayFactory == null) {
            throw new IllegalArgumentException("SfGatewayFactory can not be null");
        }
        this.gatewayFactory = gatewayFactory;

        if (aroundIteratorFactory == null) {
            throw new IllegalArgumentException("SfAroundIteratorFactory can not be null");
        }
        this.aroundIteratorFactory = aroundIteratorFactory;

        if (validateManager == null) {
            throw new IllegalArgumentException("SfValidateManager can not be null");
        }
        this.validateManager = validateManager;

        if (componentExecutionIdGenerator == null) {
            throw new IllegalArgumentException("SfComponentExecutionIdGenerator can not be null");
        }
        this.componentExecutionIdGenerator = componentExecutionIdGenerator;

        if (processExecutionIdGenerator == null) {
            throw new IllegalArgumentException("SfProcessExecutionIdGenerator can not be null");
        }
        this.processExecutionIdGenerator = processExecutionIdGenerator;

        if (contextFactory == null) {
            throw new IllegalArgumentException("SfContextFactory can not be null");
        }
        this.contextFactory = contextFactory;

        this.edgeAspectList = edgeAspectList;
        this.nodeAspectList = nodeAspectList;
        this.processAspectList = processAspectList;
    }

    @Override
    public SfProcessReturn<PEI> runProcess(PC processConfig,
                                           PEI executionId,
                                           Map<String, Object> params,
                                           Map<String, Object> env) {
        if (processConfig == null) {
            throw new NullPointerException("processConfig can not be null");
        }

        SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext = contextFactory.createProcessContext();
        processContext.setProcessConfig(processConfig);

        PEI actualExecutionId;
        if (executionId != null) {
            actualExecutionId = executionId;
        } else {
            actualExecutionId = processExecutionIdGenerator.generateProcessExecutionId(processContext);
        }
        processContext.setProcessExecutionId(actualExecutionId);

        SfProcessVariableContext processVariableContext = new SfDefaultProcessVariableContext();
        if (CollectionUtils.isNotEmpty(processAspectList)) {
            for (SfProcessAspect<NI, EI, PCI,
                    NC, EC, PCG,
                    PC, PEI> processAspect : processAspectList) {
                try {
                    processAspect.before(params, processContext, processVariableContext);
                } catch (Exception e) {
                    LOG.error("processAspect occur error", e);
                }
            }
        }

        SfValidationGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validationGlobalContext =
                createValidationGlobalContext(processConfig, contextFactory);
        validateManager.validate(processConfig, processContext, validationGlobalContext, processEngineConfig);

        SfExecutionGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionGlobalContext =
                createExecutionGlobalContext(processConfig,
                                             processContext,
                                             processVariableContext,
                                             validationGlobalContext,
                                             contextFactory);

        return null;
    }


    private SfExecutionGlobalContext<NI, EI, PCI,
            NC, EC,
            PCG, PC, NEI,
            EEI, PEI> createExecutionGlobalContext(
            PC processConfig,
            SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
            SfVariableContext processVariableContext,
            SfValidationGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validationGlobalContext,
            SfContextFactory<NI, EI, PCI,
                    NC, EC,
                    PCG, PC,
                    NEI, EEI, PEI> contextFactory) {
        SfExecutionGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionGlobalContext = contextFactory.createExecutionGlobalContext();

        SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainProcessValidationContext = validationGlobalContext.getMainProcessValidationContext();
        SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionProcessContext = contextFactory.createExecutionProcessContext();
        executionGlobalContext.setMainExecutionProcessContext(mainExecutionProcessContext);
        SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionProcessInternalContext = contextFactory.createExecutionProcessInternalContext();
        mainExecutionProcessInternalContext.setProcessContext(processContext);
        mainExecutionProcessInternalContext.setProcessConfigIndex(SfProcessConfigIndexConstant.MAIN_PROCESS_CONFIG_INDEX);
        mainExecutionProcessContext.setExecutionInternalContext(mainExecutionProcessInternalContext);
        SfExecutionProcessExternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionProcessExternalContext = contextFactory.createExecutionProcessExternalContext();
        mainExecutionProcessExternalContext.setProcessVariableContext(processVariableContext);
        mainExecutionProcessContext.setExecutionExternalContext(mainExecutionProcessExternalContext);

        List<PCG> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            int size = subProcessConfigList.size();
            List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList = new ArrayList<>(
                    size);
            executionGlobalContext.setSubExecutionProcessContextList(subExecutionProcessContextList);
            for (int i = 0; i < size; i++) {
                SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> subExecutionProcessContext = contextFactory.createExecutionProcessContext();
                subExecutionProcessContextList.add(subExecutionProcessContext);

                subExecutionProcessContext.setExecutionInternalContext(contextFactory.createExecutionProcessInternalContext());
                subExecutionProcessContext.setExecutionExternalContext(contextFactory.createExecutionProcessExternalContext());
            }
        }

        return executionGlobalContext;
    }

    @Override
    public SfProcessReturn<PEI> runProcess(PC processConfig,
                                           Map<String, Object> params,
                                           Map<String, Object> env) {
        return runProcess(processConfig, null, params, env);
    }

    @Override
    public SfProcessReturn<PEI> runProcess(PC processConfig, Map<String, Object> params) {
        return runProcess(processConfig, params, null);
    }

    public SfValidationGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>
    createValidationGlobalContext(PC processConfig,
                                  SfContextFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> contextFactory) {
        SfValidationGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validationGlobalContext = contextFactory.createValidationGlobalContext();
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainProcessValidationContext = contextFactory.createProcessValidationContext();
        validationGlobalContext.setMainProcessValidationContext(mainProcessValidationContext);

        List<PCG> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            int size = subProcessConfigList.size();
            List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList = new ArrayList<>(
                    size);
            validationGlobalContext.setSubValidationProcessContextList(subExecutionProcessContextList);
            for (int i = 0; i < size; i++) {
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> processValidationContext = contextFactory.createProcessValidationContext();
                subExecutionProcessContextList.add(processValidationContext);
            }
        }
        return validationGlobalContext;
    }
}
