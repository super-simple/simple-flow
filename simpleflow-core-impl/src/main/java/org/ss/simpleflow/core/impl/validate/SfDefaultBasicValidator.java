package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.ArrayUtils;
import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.MapUtils;
import org.ss.simpleflow.common.MultiMapUtils;
import org.ss.simpleflow.core.component.SfComponentConfig;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.context.SfValidationProcessContext;
import org.ss.simpleflow.core.context.SfValidationWholeContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.*;
import org.ss.simpleflow.core.impl.util.StackUtils;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.node.SfNodeParameter;
import org.ss.simpleflow.core.node.SfNodeResult;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfEdgeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfOrphanComponentCleaner;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;

import java.util.*;

public class SfDefaultBasicValidator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    private final SfDefaultNodeConfigValidator<NI, EI, PCI, NC, EC, PC, PEI> nodeConfigValidator;
    private final SfDefaultEdgeConfigValidator<NI, EI, PCI, NC, EC, PC, PEI> edgeConfigValidator;
    private final SfDefaultProcessConfigValidator<NI, EI, PCI, NC, EC, PC, PEI> processConfigValidator;
    private final SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PC, PEI> nodeConfigCustomValidator;
    private final SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PC, PEI> edgeConfigCustomValidator;
    private final SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PC, PEI> processConfigCustomValidate;

    private final SfOrphanComponentCleaner<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> orphanComponentCleaner;

    public SfDefaultBasicValidator(SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PC, PEI> nodeConfigCustomValidator,
                                   SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PC, PEI> edgeConfigCustomValidator,
                                   SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PC, PEI> processConfigCustomValidate) {
        this.nodeConfigCustomValidator = nodeConfigCustomValidator;
        this.edgeConfigCustomValidator = edgeConfigCustomValidator;
        this.processConfigCustomValidate = processConfigCustomValidate;

        this.nodeConfigValidator = new SfDefaultNodeConfigValidator<>();
        this.edgeConfigValidator = new SfDefaultEdgeConfigValidator<>();
        this.processConfigValidator = new SfDefaultProcessConfigValidator<>();

        this.orphanComponentCleaner = new SfDefaultOrphanComponentCleaner<>();
    }

    public void basicValidate(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                              SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext,
                              SfValidationWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> validationGlobalContext,
                              SfProcessEngineConfig processEngineConfig) {
        processConfigValidator.basicValidate(wholeProcessConfig, processContext, processEngineConfig);

        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        List<NC> nodeConfigList = mainProcessConfig.getNodeConfigList();
        List<EC> edgeConfigList = mainProcessConfig.getEdgeConfigList();
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainProcessValidationContext = validationGlobalContext.getMainProcessValidationContext();
        validateNodeAndEdge(nodeConfigList,
                            edgeConfigList,
                            mainProcessConfig,
                            processContext,
                            mainProcessValidationContext,
                            processEngineConfig);

        List<PC> subProcessConfigList = wholeProcessConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> subValidationProcessContextList = validationGlobalContext.getSubValidationProcessContextList();
            int size = subProcessConfigList.size();
            for (int i = 0; i < size; i++) {
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> subValidationProcessContext = subValidationProcessContextList.get(
                        i);
                PC subProcessConfig = subProcessConfigList.get(i);
                validateNodeAndEdge(subProcessConfig.getNodeConfigList(),
                                    subProcessConfig.getEdgeConfigList(),
                                    subProcessConfig,
                                    processContext,
                                    subValidationProcessContext,
                                    processEngineConfig);
            }
        }

        processConfigCustomValidate.customValidate(wholeProcessConfig, processContext, processEngineConfig);

        if (processEngineConfig.isCleanOrphanNode()) {
            orphanComponentCleaner.cleanOrphanComponent(wholeProcessConfig,
                                                        processContext,
                                                        validationGlobalContext,
                                                        processEngineConfig);

        }

        if (processEngineConfig.isCleanOrphanNode()) {
            orphanComponentValidate(wholeProcessConfig, processContext, validationGlobalContext, processEngineConfig);
        }

        collectReferencedSubProcess(wholeProcessConfig, processContext, validationGlobalContext, processEngineConfig);

        cleanUnreferencedSubProcess(wholeProcessConfig, validationGlobalContext);
    }

    private void orphanComponentValidate(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                                         SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext,
                                         SfValidationWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> validationGlobalContext,
                                         SfProcessEngineConfig processEngineConfig) {
        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        List<NC> nodeConfigList = mainProcessConfig.getNodeConfigList();
        List<EC> edgeConfigList = mainProcessConfig.getEdgeConfigList();

        orphanComponentValidate(nodeConfigList, edgeConfigList, processContext,
                                validationGlobalContext.getMainProcessValidationContext(), processEngineConfig);

        List<PC> subProcessConfigList = wholeProcessConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            int size = subProcessConfigList.size();
            List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> subValidationProcessContextList = validationGlobalContext.getSubValidationProcessContextList();
            for (int i = 0; i < size; i++) {
                PC subProcessConfig = subProcessConfigList.get(i);
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> subValidationGlobalContext = subValidationProcessContextList.get(
                        i);
                List<NC> subNodeConfigList = subProcessConfig.getNodeConfigList();
                List<EC> subEdgeConfigList = subProcessConfig.getEdgeConfigList();
                orphanComponentValidate(subNodeConfigList,
                                        subEdgeConfigList,
                                        processContext,
                                        subValidationGlobalContext,
                                        processEngineConfig);
            }
        }
    }

    private void orphanComponentValidate(List<NC> nodeConfigList,
                                         List<EC> edgeConfigList,
                                         SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext,
                                         SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> processValidationContext,
                                         SfProcessEngineConfig processEngineConfig) {
        int edgeConfigListSize = edgeConfigList.size();
        if (CollectionUtils.isNullOrEmpty(edgeConfigList)) {
            if (edgeConfigListSize != 1) {
                throw new SfProcessConfigException(SfProcessConfigExceptionCode.EXIST_ORPHAN_COMPONENT,
                                                   processContext,
                                                   processEngineConfig);
            }
            return;
        }

        List<EC> controlEdgeList = CollectionUtils.collect(edgeConfigList,
                                                           SfAbstractEdgeConfig::isControlEdge, edgeConfigListSize / 2);
        Map<NI, List<EC>> outgoingControlEdgeMap = MultiMapUtils.index(controlEdgeList,
                                                                       SfAbstractEdgeConfig::getFromNodeId);

        Set<NI> visitedNodeSet = new HashSet<>();
        Deque<SfComponentConfig> stack = new ArrayDeque<>();

        int nodeConfigCount = 1;
        int edgeConfigCount = 0;
        stack.push(processValidationContext.getStartNodeConfig());
        Map<NI, NC> nodeConfigMap = processValidationContext.getNodeConfigMap();

        int nodeConfigListSize = nodeConfigList.size();
        while (!stack.isEmpty()) {
            SfComponentConfig current = stack.pop();
            if (current instanceof SfAbstractNodeConfig) {
                @SuppressWarnings("unchecked") NC nodeConfig = (NC) current;
                NI nodeId = nodeConfig.getId();
                if (!visitedNodeSet.contains(nodeId)) {
                    visitedNodeSet.add(nodeId);
                    List<EC> componentCollection = outgoingControlEdgeMap.get(nodeId);
                    if (CollectionUtils.isNotEmpty(componentCollection)) {
                        edgeConfigCount += componentCollection.size();
                    }
                    StackUtils.pushAllToStack(stack, componentCollection);
                }
            } else {
                @SuppressWarnings("unchecked") EC edgeConfig = (EC) current;
                nodeConfigCount++;
                stack.push(nodeConfigMap.get(edgeConfig.getToNodeId()));
            }
        }

        if (nodeConfigCount != nodeConfigListSize || edgeConfigCount != edgeConfigListSize) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.EXIST_ORPHAN_COMPONENT,
                                               processContext,
                                               processEngineConfig);
        }
    }

    private void collectReferencedSubProcess(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                                             SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext,
                                             SfValidationWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> validationGlobalContext,
                                             SfProcessEngineConfig processEngineConfig) {
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainProcessValidationContext = validationGlobalContext.getMainProcessValidationContext();
        Set<PCI> mainSubProcessConfigIdSet = mainProcessValidationContext.getSubProcessConfigIdSet();
        List<PC> subProcessConfigList = wholeProcessConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(mainSubProcessConfigIdSet)) {
            Set<PCI> referencedSubProcessConfigIdSet = new HashSet<>();
            Map<PCI, Set<PCI>> subProcessContainProcessConfigIdMap = new HashMap<>();
            PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
            subProcessContainProcessConfigIdMap.put(mainProcessConfig.getId(), mainSubProcessConfigIdSet);

            int size = subProcessConfigList.size();
            List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> subValidationProcessContextList = validationGlobalContext.getSubValidationProcessContextList();
            for (int i = 0; i < size; i++) {
                PC porcessConfig = subProcessConfigList.get(i);
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> subValidationProcessContext = subValidationProcessContextList.get(
                        i);
                subProcessContainProcessConfigIdMap.put(porcessConfig.getId(),
                                                        subValidationProcessContext.getSubProcessConfigIdSet());
            }

            Deque<PCI> stack = new ArrayDeque<>();
            StackUtils.pushAllToStack(stack, mainSubProcessConfigIdSet);
            while (!stack.isEmpty()) {
                PCI pop = stack.pop();
                if (subProcessContainProcessConfigIdMap.containsKey(pop)) {
                    referencedSubProcessConfigIdSet.add(pop);
                    Set<PCI> processConfigIdSet = subProcessContainProcessConfigIdMap.get(pop);
                    StackUtils.pushAllToStack(stack, processConfigIdSet);
                } else {
                    throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_SUB_PROCESS,
                                                       processContext,
                                                       processEngineConfig);
                }
            }

            validationGlobalContext.setSubProcessContainProcessConfigIdMap(subProcessContainProcessConfigIdMap);
            validationGlobalContext.setReferencedSubProcessConfigIdSet(referencedSubProcessConfigIdSet);
        }
    }

    private void cleanUnreferencedSubProcess(
            SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
            SfValidationWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> validationGlobalContext) {
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainProcessValidationContext = validationGlobalContext.getMainProcessValidationContext();
        Set<PCI> subProcessConfigIdSet = mainProcessValidationContext.getSubProcessConfigIdSet();
        if (CollectionUtils.isNullOrEmpty(subProcessConfigIdSet)) {
            wholeProcessConfig.setSubProcessConfigList(null);
            validationGlobalContext.setSubValidationProcessContextList(null);
        } else {
            Set<PCI> referencedSubProcessConfigIdSet = validationGlobalContext.getReferencedSubProcessConfigIdSet();
            List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> subValidationProcessContextList = validationGlobalContext.getSubValidationProcessContextList();
            List<PC> subProcessConfigList = wholeProcessConfig.getSubProcessConfigList();
            Iterator<PC> subProcessConfigListIterator = subProcessConfigList.iterator();
            Iterator<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> subExecutionProcessContextListIterator = subValidationProcessContextList.iterator();
            while (subProcessConfigListIterator.hasNext()) {
                PC subProcessConfig = subProcessConfigListIterator.next();
                PCI subProcessConfigId = subProcessConfig.getId();
                if (!referencedSubProcessConfigIdSet.contains(subProcessConfigId)) {
                    subProcessConfigListIterator.remove();
                    subExecutionProcessContextListIterator.remove();
                }
            }
        }
    }

    private void validateNodeAndEdge(List<NC> nodeConfigList,
                                     List<EC> edgeConfigList,
                                     PC processConfig,
                                     SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext,
                                     SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> processValidationContext,
                                     SfProcessEngineConfig processEngineConfig) {
        Set<NI> nodeIdSet = validateNode(nodeConfigList,
                                         processConfig,
                                         processContext,
                                         processValidationContext,
                                         processEngineConfig);

        validateEdge(nodeConfigList,
                     nodeIdSet,
                     edgeConfigList,
                     processConfig,
                     processContext,
                     processValidationContext,
                     processEngineConfig
        );
    }

    private void validateEdge(List<NC> nodeConfigList,
                              Set<NI> nodeIdSet,
                              List<EC> edgeConfigList,
                              PC processConfig,
                              SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext,
                              SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> processValidationContext,
                              SfProcessEngineConfig processEngineConfig) {
        Map<NI, NC> nodeConfigMap = MapUtils.uniqueIndex(nodeConfigList,
                                                         SfAbstractNodeConfig::getId);

        processValidationContext.setNodeConfigMap(nodeConfigMap);

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

                                                    processContext,
                                                    processEngineConfig);
                }
                edgeIdSet.add(edgeId);

                NI fromNodeId = edgeConfig.getFromNodeId();
                if (!nodeIdSet.contains(fromNodeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_FROM_NI,
                                                    edgeConfig,
                                                    processConfig,

                                                    processContext,
                                                    processEngineConfig);
                }
                NI toNodeId = edgeConfig.getToNodeId();
                if (!nodeIdSet.contains(toNodeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_TO_NI,
                                                    edgeConfig,
                                                    processConfig,

                                                    processContext,
                                                    processEngineConfig);
                }
                if (edgeConfig.isControlEdge()) {
                    String uniqueKey = fromNodeId.toString() + toNodeId.toString();
                    if (controlEdgeDistinctIdSet.contains(uniqueKey)) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.CONTROL_EDGE_REPEAT,
                                                        edgeConfig,
                                                        processConfig,

                                                        processContext,
                                                        processEngineConfig);
                    } else {
                        controlEdgeDistinctIdSet.add(uniqueKey);
                    }
                } else {
                    int fromResultIndex = edgeConfig.getFromResultIndex();
                    NC fromNodeConfig = nodeConfigMap.get(fromNodeId);
                    SfNodeResult[] result = fromNodeConfig.getResult();
                    boolean wrongFromResultKey = false;
                    if (ArrayUtils.isNullOrEmpty(result)) {
                        wrongFromResultKey = true;
                    } else {
                        int maxIndex = result.length - 1;
                        if (fromResultIndex < 0 || fromResultIndex > maxIndex) {
                            wrongFromResultKey = true;
                        }
                    }
                    if (wrongFromResultKey) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_FROM_RESULT_KEY,
                                                        edgeConfig,
                                                        processConfig,

                                                        processContext,
                                                        processEngineConfig);
                    }

                    int toParameterIndex = edgeConfig.getToParameterIndex();
                    NC toNodeConfig = nodeConfigMap.get(toNodeId);
                    SfNodeParameter[] parameter = toNodeConfig.getParameter();
                    boolean wrongToParameterKey = false;
                    if (ArrayUtils.isNullOrEmpty(parameter)) {
                        wrongToParameterKey = true;
                    } else {
                        int maxIndex = parameter.length - 1;
                        if (toParameterIndex < 0 || toParameterIndex > maxIndex) {
                            wrongToParameterKey = true;
                        }
                    }
                    if (wrongToParameterKey) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_TO_PARAMETER_KEY,
                                                        edgeConfig,
                                                        processConfig,

                                                        processContext,
                                                        processEngineConfig);
                    }

                    String uniqueKey = fromNodeId.toString() + fromResultIndex +
                            toNodeId.toString() + toParameterIndex;
                    if (dataEdgeDistinctIdSet.contains(uniqueKey)) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.DATA_EDGE_REPEAT,
                                                        edgeConfig,
                                                        processConfig,

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
                                 SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext,
                                 SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> processValidationContext,
                                 SfProcessEngineConfig processEngineConfig) {
        if (CollectionUtils.isNullOrEmpty(nodeConfigList)) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_NODE,
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
                                                    processContext,
                                                    processEngineConfig);
                }
            }

            if (nodeConfig.isSubProcessNode()) {
                if (!processEngineConfig.isCleanOrphanNode()) {
                    Set<PCI> subProcessConfigIdSet = getSubProcessConfigIdSet(processValidationContext);
                    subProcessConfigIdSet.add(nodeConfig.getProcessId());
                }
            }
        }

        if (startNodeConfig == null) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_START_EVENT,
                                               processContext,
                                               processEngineConfig);
        }

        processValidationContext.setStartNodeConfig(startNodeConfig);
        return nodeIdSet;
    }

    public static <NI, EI, PCI,
            NC extends SfAbstractNodeConfig<NI, PCI>,
            EC extends SfAbstractEdgeConfig<EI, NI>,

            PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
            NEI, EEI, PEI> Set<PCI> getSubProcessConfigIdSet(
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> processValidationContext) {
        Set<PCI> subProcessConfigIdSet = processValidationContext.getSubProcessConfigIdSet();
        if (CollectionUtils.isNullOrEmpty(subProcessConfigIdSet)) {
            subProcessConfigIdSet = new HashSet<>();
            processValidationContext.setSubProcessConfigIdSet(subProcessConfigIdSet);
        }
        return subProcessConfigIdSet;
    }

}
