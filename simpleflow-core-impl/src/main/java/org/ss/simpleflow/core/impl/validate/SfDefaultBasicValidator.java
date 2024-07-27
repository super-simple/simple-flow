package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.MapUtils;
import org.ss.simpleflow.core.context.SfExecutionGlobalContext;
import org.ss.simpleflow.core.context.SfExecutionProcessContext;
import org.ss.simpleflow.core.context.SfExecutionProcessInternalContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.*;
import org.ss.simpleflow.core.impl.util.StackUtils;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.node.SfNodeParameter;
import org.ss.simpleflow.core.node.SfNodeResult;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfEdgeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfOrphanComponentCleaner;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;

import java.util.*;

public class SfDefaultBasicValidator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    private final SfDefaultNodeConfigValidator<NI, EI, PCI, NC, EC, PCG, PC, PEI> nodeConfigValidator;
    private final SfDefaultEdgeConfigValidator<NI, EI, PCI, NC, EC, PCG, PC, PEI> edgeConfigValidator;
    private final SfDefaultProcessConfigValidator<NI, EI, PCI, NC, EC, PCG, PC, PEI> processConfigValidator;
    private final SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PCG, PC, PEI> nodeConfigCustomValidator;
    private final SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PCG, PC, PEI> edgeConfigCustomValidator;
    private final SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PCG, PC, PEI> processConfigCustomValidate;

    private final SfOrphanComponentCleaner<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> orphanComponentCleaner;

    public SfDefaultBasicValidator(SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PCG, PC, PEI> nodeConfigCustomValidator,
                                   SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PCG, PC, PEI> edgeConfigCustomValidator,
                                   SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PCG, PC, PEI> processConfigCustomValidate) {
        this.nodeConfigCustomValidator = nodeConfigCustomValidator;
        this.edgeConfigCustomValidator = edgeConfigCustomValidator;
        this.processConfigCustomValidate = processConfigCustomValidate;

        this.nodeConfigValidator = new SfDefaultNodeConfigValidator<>();
        this.edgeConfigValidator = new SfDefaultEdgeConfigValidator<>();
        this.processConfigValidator = new SfDefaultProcessConfigValidator<>();

        this.orphanComponentCleaner = new SfDefaultOrphanComponentCleaner<>();
    }

    public void basicValidate(PC processConfig,
                              SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
                              SfExecutionGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionGlobalContext,
                              SfProcessEngineConfig processEngineConfig) {
        processConfigValidator.basicValidate(processConfig, processContext, processEngineConfig);

        List<NC> nodeConfigList = processConfig.getNodeConfigList();
        List<EC> edgeConfigList = processConfig.getEdgeConfigList();
        SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionProcessContext = executionGlobalContext.getMainExecutionProcessContext();
        validateNodeAndEdge(nodeConfigList,
                            edgeConfigList,
                            processConfig,
                            null,
                            processContext,
                            mainExecutionProcessContext,
                            processEngineConfig);

        List<PCG> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList = executionGlobalContext.getSubExecutionProcessContextList();
            int size = subProcessConfigList.size();
            for (int i = 0; i < size; i++) {
                SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> subExecutionProcessContext = subExecutionProcessContextList.get(
                        i);
                PCG processConfigGraph = subProcessConfigList.get(i);
                validateNodeAndEdge(processConfigGraph.getNodeConfigList(), processConfigGraph.getEdgeConfigList(),
                                    processConfig, processConfigGraph,
                                    processContext,
                                    subExecutionProcessContext,
                                    processEngineConfig);
            }
        }

        collectReferencedSubProcessAndValidate(executionGlobalContext,
                                               mainExecutionProcessContext,
                                               subProcessConfigList,
                                               processConfig,
                                               processContext,
                                               processEngineConfig);

        if (processEngineConfig.isCleanOrphanNode()) {
            orphanComponentCleaner.cleanOrphanComponent(processConfig,
                                                        processContext,
                                                        executionGlobalContext,
                                                        processEngineConfig);
        }

        cleanUnreferencedSubProcess(processConfig, subProcessConfigList, executionGlobalContext);

        processConfigCustomValidate.customValidate(processConfig, processContext, processEngineConfig);
    }

    private void cleanUnreferencedSubProcess(
            PC processConfig,
            List<PCG> subProcessConfigList,
            SfExecutionGlobalContext<NI, EI, PCI,
                    NC, EC,
                    PCG, PC,
                    NEI, EEI, PEI> executionGlobalContext
    ) {
        Set<PCI> referencedSubProcessConfigIdSet = executionGlobalContext.getReferencedSubProcessConfigIdSet();
        if (CollectionUtils.isNullOrEmpty(referencedSubProcessConfigIdSet)) {
            processConfig.setSubProcessConfigList(null);
            executionGlobalContext.setSubExecutionProcessContextList(null);
        } else {
            List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList = executionGlobalContext.getSubExecutionProcessContextList();
            Iterator<PCG> subProcessConfigListIterator = subProcessConfigList.iterator();
            Iterator<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextListIterator = subExecutionProcessContextList.iterator();
            while (subProcessConfigListIterator.hasNext()) {
                PCG subProcessConfig = subProcessConfigListIterator.next();
                PCI subProcessConfigId = subProcessConfig.getId();
                if (!referencedSubProcessConfigIdSet.contains(subProcessConfigId)) {
                    subProcessConfigListIterator.remove();
                    subExecutionProcessContextListIterator.remove();
                }
            }
        }
    }

    private void collectReferencedSubProcessAndValidate(
            SfExecutionGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionGlobalContext,
            SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionProcessContext,
            List<PCG> subProcessConfigList,
            PC processConfig,
            SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
            SfProcessEngineConfig processEngineConfig) {
        SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionInternalContext = mainExecutionProcessContext.getExecutionInternalContext();
        Set<PCI> mainSubProcessConfigIdSet = mainExecutionInternalContext.getSubProcessConfigIdSet();
        if (CollectionUtils.isNotEmpty(mainSubProcessConfigIdSet)) {
            Set<PCI> referencedSubProcessConfigIdSet = new HashSet<>();
            Map<PCI, Set<PCI>> collectSubProcessConfigIdMap = new HashMap<>();
            int size = subProcessConfigList.size();

            List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList = executionGlobalContext.getSubExecutionProcessContextList();
            for (int i = 0; i < size; i++) {
                PCG processConfigGraph = subProcessConfigList.get(i);
                SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> subExecutionProcessContext = subExecutionProcessContextList.get(
                        i);
                collectSubProcessConfigIdMap.put(processConfigGraph.getId(),
                                                 subExecutionProcessContext.getExecutionInternalContext().getSubProcessConfigIdSet());
            }

            Deque<PCI> stack = new ArrayDeque<>();
            StackUtils.pushAllToStack(stack, mainSubProcessConfigIdSet);
            while (!stack.isEmpty()) {
                PCI pop = stack.pop();
                if (collectSubProcessConfigIdMap.containsKey(pop)) {
                    referencedSubProcessConfigIdSet.add(pop);
                    Set<PCI> processConfigIdSet = collectSubProcessConfigIdMap.get(pop);
                    StackUtils.pushAllToStack(stack, processConfigIdSet);
                } else {
                    throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_SUB_PROCESS,
                                                       null,
                                                       null,
                                                       processContext,
                                                       processEngineConfig);
                }
            }

            executionGlobalContext.setReferencedSubProcessConfigIdSet(referencedSubProcessConfigIdSet);

            collectSubProcessConfigIdMap.put(processConfig.getId(), mainSubProcessConfigIdSet);
            for (Map.Entry<PCI, Set<PCI>> entry : collectSubProcessConfigIdMap.entrySet()) {
                PCI processConfigId = entry.getKey();
                Set<PCI> referencedProcessConfigIdSet = entry.getValue();
                if (CollectionUtils.isNotEmpty(referencedProcessConfigIdSet)) {
                    for (PCI referencedProcessConfigId : referencedProcessConfigIdSet) {
                        Set<PCI> processConfigIdSet = collectSubProcessConfigIdMap.get(
                                referencedProcessConfigId);
                        if (CollectionUtils.isNotEmpty(processConfigIdSet)) {
                            if (processConfigIdSet.contains(processConfigId)) {
                                throw new SfProcessConfigException(SfProcessConfigExceptionCode.CIRCULAR_REFERENCE,
                                                                   null,
                                                                   null,
                                                                   processContext,
                                                                   processEngineConfig);
                            }
                        }
                    }
                }
            }
        }
    }

    private void validateNodeAndEdge(List<NC> nodeConfigList,
                                     List<EC> edgeConfigList,
                                     PC processConfig, PCG processConfigGraph,
                                     SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
                                     SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionProcessContext,
                                     SfProcessEngineConfig processEngineConfig) {
        Set<NI> nodeIdSet = validateNode(nodeConfigList,
                                         processConfig,
                                         processConfigGraph,
                                         processContext,
                                         executionProcessContext,
                                         processEngineConfig);

        validateEdge(nodeConfigList,
                     nodeIdSet,
                     edgeConfigList,
                     processConfig,
                     processConfigGraph,
                     processContext,
                     executionProcessContext,
                     processEngineConfig
        );
    }

    private void validateEdge(List<NC> nodeConfigList,
                              Set<NI> nodeIdSet,
                              List<EC> edgeConfigList,
                              PC processConfig,
                              PCG processConfigGraph,
                              SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
                              SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionProcessContext,
                              SfProcessEngineConfig processEngineConfig) {
        Map<NI, NC> nodeConfigMap = MapUtils.uniqueIndex(nodeConfigList,
                                                         SfAbstractNodeConfig::getId);

        SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionInternalContext = executionProcessContext.getExecutionInternalContext();
        executionInternalContext.setNodeConfigMap(nodeConfigMap);

        if (CollectionUtils.isNotEmpty(edgeConfigList)) {
            Set<EI> edgeIdSet = new HashSet<>(edgeConfigList.size());
            Set<String> controlEdgeDistinctIdSet = new HashSet<>();
            Set<String> dataEdgeDistinctIdSet = new HashSet<>();
            for (EC edgeConfig : edgeConfigList) {
                edgeConfigValidator.basicValidate(edgeConfig, processConfig, processContext, processEngineConfig);
                if (edgeConfigCustomValidator != null) {
                    edgeConfigCustomValidator.customValidate(edgeConfig,
                                                             processConfig,
                                                             processContext,
                                                             processEngineConfig);
                }

                EI edgeId = edgeConfig.getId();
                if (edgeIdSet.contains(edgeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.ID_REPEAT,
                                                    edgeConfig,
                                                    processConfig,
                                                    processConfigGraph,
                                                    processContext,
                                                    processEngineConfig);
                }
                edgeIdSet.add(edgeId);

                NI fromNodeId = edgeConfig.getFromNodeId();
                if (!nodeIdSet.contains(fromNodeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_FROM_NI,
                                                    edgeConfig,
                                                    processConfig,
                                                    processConfigGraph,
                                                    processContext,
                                                    processEngineConfig);
                }
                NI toNodeId = edgeConfig.getToNodeId();
                if (!nodeIdSet.contains(toNodeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_TO_NI,
                                                    edgeConfig,
                                                    processConfig,
                                                    processConfigGraph,
                                                    processContext,
                                                    processEngineConfig);
                }
                if (edgeConfig.isControlEdge()) {
                    String uniqueKey = fromNodeId.toString() + toNodeId.toString();
                    if (controlEdgeDistinctIdSet.contains(uniqueKey)) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.CONTROL_EDGE_REPEAT,
                                                        edgeConfig,
                                                        processConfig,
                                                        processConfigGraph,
                                                        processContext,
                                                        processEngineConfig);
                    } else {
                        controlEdgeDistinctIdSet.add(uniqueKey);
                    }
                } else {
                    String fromResultKey = edgeConfig.getFromResultKey();
                    NC fromNodeConfig = nodeConfigMap.get(fromNodeId);
                    Map<String, SfNodeResult> resultMap = fromNodeConfig.getResultMap();
                    boolean wrongFromResultKey = false;
                    if (MapUtils.isNullOrEmpty(resultMap)) {
                        wrongFromResultKey = true;
                    } else {
                        if (!resultMap.containsKey(fromResultKey)) {
                            wrongFromResultKey = true;
                        }
                    }
                    if (wrongFromResultKey) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_FROM_RESULT_KEY,
                                                        edgeConfig,
                                                        processConfig,
                                                        processConfigGraph,
                                                        processContext,
                                                        processEngineConfig);
                    }

                    String toParameterKey = edgeConfig.getToParameterKey();
                    NC toNodeConfig = nodeConfigMap.get(toNodeId);
                    Map<String, SfNodeParameter> parameterMap = toNodeConfig.getParameterMap();
                    boolean wrongToParameterKey = false;
                    if (MapUtils.isNullOrEmpty(parameterMap)) {
                        wrongToParameterKey = true;
                    } else {
                        if (!parameterMap.containsKey(toParameterKey)) {
                            wrongToParameterKey = true;
                        }
                    }
                    if (wrongToParameterKey) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_TO_PARAMETER_KEY,
                                                        edgeConfig,
                                                        processConfig,
                                                        processConfigGraph,
                                                        processContext,
                                                        processEngineConfig);
                    }

                    String uniqueKey = fromNodeId.toString() + fromResultKey +
                            toNodeId.toString() + toParameterKey;
                    if (dataEdgeDistinctIdSet.contains(uniqueKey)) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.DATA_EDGE_REPEAT,
                                                        edgeConfig,
                                                        processConfig,
                                                        processConfigGraph,
                                                        processContext,
                                                        processEngineConfig);
                    } else {
                        dataEdgeDistinctIdSet.add(uniqueKey);
                    }
                }
            }
        }
    }

    private Set<NI> validateNode(List<NC> nodeConfigList,
                                 PC processConfig,
                                 PCG processConfigGraph,
                                 SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
                                 SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionProcessContext,
                                 SfProcessEngineConfig processEngineConfig) {
        if (CollectionUtils.isNullOrEmpty(nodeConfigList)) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_NODE,
                                               processConfig,
                                               processConfigGraph,
                                               processContext,
                                               processEngineConfig);
        }

        Set<NI> nodeIdSet = new HashSet<>(nodeConfigList.size());

        NC startNodeConfig = null;

        for (NC nodeConfig : nodeConfigList) {
            nodeConfigValidator.basicValidate(nodeConfig, processConfig, processContext, processEngineConfig);
            if (nodeConfigCustomValidator != null) {
                nodeConfigCustomValidator.customValidate(nodeConfig,
                                                         processConfig,
                                                         processContext,
                                                         processEngineConfig);
            }

            NI nodeId = nodeConfig.getId();
            if (nodeIdSet.contains(nodeId)) {
                throw new SfNodeConfigException(SfNodeConfigExceptionCode.ID_REPEAT,
                                                nodeConfig,
                                                processConfig,
                                                processConfigGraph,
                                                processContext,
                                                processEngineConfig);
            }
            nodeIdSet.add(nodeId);

            if (nodeConfig.isStartNode()) {
                if (startNodeConfig == null) {
                    startNodeConfig = nodeConfig;
                } else {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.START_EVENT_REPEAT,
                                                    nodeConfig,
                                                    processConfig,
                                                    processConfigGraph,
                                                    processContext,
                                                    processEngineConfig);
                }
            }

            if (nodeConfig.isSubProcessNode()) {
                Set<PCI> subProcessConfigIdSet = getSubProcessConfigIdSet(executionProcessContext);
                subProcessConfigIdSet.add(nodeConfig.getProcessId());
            }
        }

        if (startNodeConfig == null) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_START_EVENT,
                                               processConfig,
                                               processConfigGraph,
                                               processContext,
                                               processEngineConfig);
        }

        SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionInternalContext = executionProcessContext.getExecutionInternalContext();
        executionInternalContext.setStartNodeConfig(startNodeConfig);
        return nodeIdSet;
    }

    private static <NI, EI, PCI,
            NC extends SfAbstractNodeConfig<NI, PCI>,
            EC extends SfAbstractEdgeConfig<EI, NI>,
            PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
            PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
            NEI, EEI, PEI> Set<PCI> getSubProcessConfigIdSet(
            SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionProcessContext) {
        SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionInternalContext = executionProcessContext.getExecutionInternalContext();
        Set<PCI> subProcessConfigIdSet = executionInternalContext.getSubProcessConfigIdSet();
        if (CollectionUtils.isNullOrEmpty(subProcessConfigIdSet)) {
            subProcessConfigIdSet = new HashSet<>();
            executionInternalContext.setSubProcessConfigIdSet(subProcessConfigIdSet);
        }
        return subProcessConfigIdSet;
    }

}
