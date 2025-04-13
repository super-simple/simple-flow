package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.ArrayUtils;
import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.MapUtils;
import org.ss.simpleflow.common.MultiMapUtils;
import org.ss.simpleflow.core.component.SfComponentConfig;
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
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    private final SfDefaultNodeConfigValidator<NI, EI, PCI, NC, EC, PC> nodeConfigValidator;
    private final SfDefaultEdgeConfigValidator<NI, EI, PCI, NC, EC, PC> edgeConfigValidator;
    private final SfDefaultProcessConfigValidator<NI, EI, PCI, NC, EC, PC> processConfigValidator;
    private final SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PC> nodeConfigCustomValidator;
    private final SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PC> edgeConfigCustomValidator;
    private final SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PC> processConfigCustomValidate;

    private final SfOrphanComponentCleaner<NI, EI, PCI, NC, EC, PC> orphanComponentCleaner;

    public SfDefaultBasicValidator(SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PC> nodeConfigCustomValidator,
                                   SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PC> edgeConfigCustomValidator,
                                   SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PC> processConfigCustomValidate) {
        this.nodeConfigCustomValidator = nodeConfigCustomValidator;
        this.edgeConfigCustomValidator = edgeConfigCustomValidator;
        this.processConfigCustomValidate = processConfigCustomValidate;

        this.nodeConfigValidator = new SfDefaultNodeConfigValidator<>();
        this.edgeConfigValidator = new SfDefaultEdgeConfigValidator<>();
        this.processConfigValidator = new SfDefaultProcessConfigValidator<>();

        this.orphanComponentCleaner = new SfDefaultOrphanComponentCleaner<>();
    }

    public void basicValidate(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                              SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext,
                              SfProcessEngineConfig processEngineConfig) {
        processConfigValidator.basicValidate(wholeProcessConfig, processEngineConfig);
        if (processConfigCustomValidate != null) {
            processConfigCustomValidate.customValidate(wholeProcessConfig, processEngineConfig);
        }

        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext = validationWholeContext.getMainValidationProcessContext();
        validateNodeAndEdge(mainProcessConfig, mainProcessValidationContext, processEngineConfig);

        PC[] subProcessConfigArray = wholeProcessConfig.getSubProcessConfigArray();
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] subValidationProcessContextArray = validationWholeContext.getSubValidationProcessContextArray();
        int length = subProcessConfigArray.length;
        for (int i = 0; i < length; i++) {
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> subValidationProcessContext = subValidationProcessContextArray[i];
            PC subProcessConfig = subProcessConfigArray[i];
            validateNodeAndEdge(subProcessConfig, subValidationProcessContext, processEngineConfig);
        }

        if (processEngineConfig.isCleanOrphanNode()) {
            orphanComponentCleaner.cleanOrphanComponent(wholeProcessConfig,
                                                        validationWholeContext,
                                                        processEngineConfig);

            orphanComponentValidate(wholeProcessConfig, validationWholeContext, processEngineConfig);
        }

        collectReferencedSubProcess(wholeProcessConfig, validationWholeContext, processEngineConfig);

        cleanUnreferencedSubProcess(wholeProcessConfig, validationWholeContext);
    }

    private void orphanComponentValidate(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                                         SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext,
                                         SfProcessEngineConfig processEngineConfig) {
        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        orphanComponentValidate(mainProcessConfig,
                                validationWholeContext.getMainValidationProcessContext(),
                                processEngineConfig);

        PC[] subProcessConfigArray = wholeProcessConfig.getSubProcessConfigArray();
        int length = subProcessConfigArray.length;
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] subValidationProcessContextArray = validationWholeContext.getSubValidationProcessContextArray();
        for (int i = 0; i < length; i++) {
            PC subProcessConfig = subProcessConfigArray[i];
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> subValidationGlobalContext = subValidationProcessContextArray[i];
            orphanComponentValidate(subProcessConfig, subValidationGlobalContext, processEngineConfig);
        }
    }

    private void orphanComponentValidate(PC processConfig,
                                         SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> processValidationContext,
                                         SfProcessEngineConfig processEngineConfig) {
        EC[] edgeConfigArray = processConfig.getEdgeConfigArray();
        NC[] nodeConfigArray = processConfig.getNodeConfigArray();
        int edgeConfigArrayLength = edgeConfigArray.length;

        EC[] controlEdgeList = ArrayUtils.collect(edgeConfigArray,
                                                  SfAbstractEdgeConfig::isControlEdge,
                                                  edgeConfigArrayLength / 2);
        Map<NI, List<EC>> outgoingControlEdgeMap = MultiMapUtils.index(controlEdgeList,
                                                                       SfAbstractEdgeConfig::getFromNodeId);

        Set<NI> visitedNodeSet = new HashSet<>();
        Deque<SfComponentConfig> stack = new ArrayDeque<>();

        int nodeConfigCount = 1;
        int edgeConfigCount = 0;
        stack.push(processValidationContext.getStartNodeConfig());
        Map<NI, NC> nodeConfigMap = processValidationContext.getNodeConfigMap();

        int nodeConfigArrayLength = nodeConfigArray.length;
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

        if (nodeConfigCount != nodeConfigArrayLength || edgeConfigCount != edgeConfigArrayLength) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.EXIST_ORPHAN_COMPONENT,
                                               processConfig,
                                               processEngineConfig);
        }
    }

    private void collectReferencedSubProcess(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                                             SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext,
                                             SfProcessEngineConfig processEngineConfig) {
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext = validationWholeContext.getMainValidationProcessContext();
        Set<PCI> mainSubProcessConfigIdSet = mainProcessValidationContext.getSubProcessConfigIdSet();
        PC[] subProcessConfigArray = wholeProcessConfig.getSubProcessConfigArray();
        if (CollectionUtils.isNotEmpty(mainSubProcessConfigIdSet)) {
            Set<PCI> referencedSubProcessConfigIdSet = new HashSet<>();
            Map<PCI, Set<PCI>> subProcessContainProcessConfigIdMap = new HashMap<>();
            PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
            subProcessContainProcessConfigIdMap.put(mainProcessConfig.getId(), mainSubProcessConfigIdSet);

            int length = subProcessConfigArray.length;
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] subValidationProcessContextArray = validationWholeContext.getSubValidationProcessContextArray();
            for (int i = 0; i < length; i++) {
                PC processConfig = subProcessConfigArray[i];
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> subValidationProcessContext = subValidationProcessContextArray[i];
                subProcessContainProcessConfigIdMap.put(processConfig.getId(),
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
                                                       mainProcessConfig,
                                                       processEngineConfig);
                }
            }

            validationWholeContext.setSubProcessContainProcessConfigIdMap(subProcessContainProcessConfigIdMap);
            validationWholeContext.setReferencedSubProcessConfigIdSet(referencedSubProcessConfigIdSet);
        }
    }

    private void cleanUnreferencedSubProcess(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                                             SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext) {
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext = validationWholeContext.getMainValidationProcessContext();
        Set<PCI> subProcessConfigIdSet = mainProcessValidationContext.getSubProcessConfigIdSet();
        if (CollectionUtils.isNullOrEmpty(subProcessConfigIdSet)) {
            wholeProcessConfig.setSubProcessConfigArray(null);
            validationWholeContext.setSubValidationProcessContextArray(null);
        } else {
            Set<PCI> referencedSubProcessConfigIdSet = validationWholeContext.getReferencedSubProcessConfigIdSet();
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] subValidationProcessContextArray = validationWholeContext.getSubValidationProcessContextArray();
            PC[] subProcessConfigArray = wholeProcessConfig.getSubProcessConfigArray();
            int length = subProcessConfigArray.length;
            int newLength = 0;
            for (int i = 0; i < length; i++) {
                PC subProcessConfig = subProcessConfigArray[i];
                PCI subProcessConfigId = subProcessConfig.getId();
                if (!referencedSubProcessConfigIdSet.contains(subProcessConfigId)) {
                    subProcessConfigArray[i] = null;
                    subValidationProcessContextArray[i] = null;
                } else {
                    newLength++;
                }
            }
            if (newLength != length) {
                @SuppressWarnings({"unchecked", "DataFlowIssue"})
                PC[] newSubProcessConfigArray = (PC[]) new Object[newLength];
                wholeProcessConfig.setSubProcessConfigArray(newSubProcessConfigArray);
                @SuppressWarnings({"unchecked", "DataFlowIssue"})
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] newSubValidationProcessContextArray = (SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[]) new SfValidationProcessContext[newLength];
                validationWholeContext.setSubValidationProcessContextArray(newSubValidationProcessContextArray);
                int index = 0;
                for (int i = 0; i < length; i++) {
                    PC pc = subProcessConfigArray[i];
                    SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> validationProcessContext = subValidationProcessContextArray[i];
                    if (pc != null) {
                        newSubProcessConfigArray[index] = pc;
                        newSubValidationProcessContextArray[index] = validationProcessContext;
                        index++;
                    }
                }
            }
        }
    }

    private void validateNodeAndEdge(PC processConfig,
                                     SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> processValidationContext,
                                     SfProcessEngineConfig processEngineConfig) {
        NC[] nodeConfigArray = processConfig.getNodeConfigArray();
        EC[] edgeConfigArray = processConfig.getEdgeConfigArray();
        Set<NI> nodeIdSet = validateNode(nodeConfigArray, processConfig, processValidationContext, processEngineConfig);

        validateEdge(nodeConfigArray,
                     nodeIdSet,
                     edgeConfigArray,
                     processConfig,
                     processValidationContext,
                     processEngineConfig);
    }

    private void validateEdge(NC[] nodeConfigArray,
                              Set<NI> nodeIdSet,
                              EC[] edgeConfigArray,
                              PC processConfig,
                              SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> processValidationContext,
                              SfProcessEngineConfig processEngineConfig) {
        Map<NI, NC> nodeConfigMap = MapUtils.uniqueIndex(nodeConfigArray, SfAbstractNodeConfig::getId);

        processValidationContext.setNodeConfigMap(nodeConfigMap);

        int length = edgeConfigArray.length;
        if (length > 0) {
            Set<EI> edgeIdSet = new HashSet<>(length);
            Set<String> controlEdgeDistinctIdSet = new HashSet<>();
            Set<String> dataEdgeDistinctIdSet = new HashSet<>();
            int controlEdgeCount = 0;
            int dataEdgeCount = 0;
            for (EC edgeConfig : edgeConfigArray) {
                edgeConfigValidator.basicValidate(edgeConfig, processConfig, processEngineConfig);
                if (edgeConfigCustomValidator != null) {
                    edgeConfigCustomValidator.customValidate(edgeConfig, processConfig, processEngineConfig);
                }

                EI edgeId = edgeConfig.getId();
                if (edgeIdSet.contains(edgeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.ID_REPEAT,
                                                    edgeConfig,
                                                    processConfig,
                                                    processEngineConfig);
                }
                edgeIdSet.add(edgeId);

                NI fromNodeId = edgeConfig.getFromNodeId();
                if (!nodeIdSet.contains(fromNodeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_FROM_NI,
                                                    edgeConfig,
                                                    processConfig,
                                                    processEngineConfig);
                }
                NI toNodeId = edgeConfig.getToNodeId();
                if (!nodeIdSet.contains(toNodeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_TO_NI,
                                                    edgeConfig,
                                                    processConfig,
                                                    processEngineConfig);
                }
                if (edgeConfig.isControlEdge()) {
                    String uniqueKey = fromNodeId.toString() + toNodeId.toString();
                    if (controlEdgeDistinctIdSet.contains(uniqueKey)) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.CONTROL_EDGE_REPEAT,
                                                        edgeConfig,
                                                        processConfig,
                                                        processEngineConfig);
                    } else {
                        controlEdgeDistinctIdSet.add(uniqueKey);
                    }
                    controlEdgeCount++;
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
                                                        processEngineConfig);
                    }

                    String uniqueKey = fromNodeId.toString() + fromResultIndex + toNodeId.toString() + toParameterIndex;
                    if (dataEdgeDistinctIdSet.contains(uniqueKey)) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.DATA_EDGE_REPEAT,
                                                        edgeConfig,
                                                        processConfig,
                                                        processEngineConfig);
                    } else {
                        dataEdgeDistinctIdSet.add(uniqueKey);
                    }
                    dataEdgeCount++;
                }
            }
            processValidationContext.setControlEdgeCount(controlEdgeCount);
            processValidationContext.setDataEdgeCount(dataEdgeCount);
        }
    }

    private Set<NI> validateNode(NC[] nodeConfigList,
                                 PC processConfig,
                                 SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> processValidationContext,
                                 SfProcessEngineConfig processEngineConfig) {
        int length = nodeConfigList.length;
        if (length == 0) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_NODE,
                                               processConfig,
                                               processEngineConfig);
        }

        Set<NI> nodeIdSet = new HashSet<>(length);

        NC startNodeConfig = null;

        for (NC nodeConfig : nodeConfigList) {
            nodeConfigValidator.basicValidate(nodeConfig, processConfig, processEngineConfig);
            if (nodeConfigCustomValidator != null) {
                nodeConfigCustomValidator.customValidate(nodeConfig, processConfig, processEngineConfig);
            }

            NI nodeId = nodeConfig.getId();
            if (nodeIdSet.contains(nodeId)) {
                throw new SfNodeConfigException(SfNodeConfigExceptionCode.ID_REPEAT,
                                                nodeConfig,
                                                processConfig,
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
                                               processConfig,
                                               processEngineConfig);
        }

        processValidationContext.setStartNodeConfig(startNodeConfig);
        return nodeIdSet;
    }

    public static <NI, EI, PCI, NC extends SfAbstractNodeConfig<NI, PCI>, EC extends SfAbstractEdgeConfig<EI, NI>, PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> Set<PCI> getSubProcessConfigIdSet(
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> processValidationContext) {
        Set<PCI> subProcessConfigIdSet = processValidationContext.getSubProcessConfigIdSet();
        if (CollectionUtils.isNullOrEmpty(subProcessConfigIdSet)) {
            subProcessConfigIdSet = new HashSet<>();
            processValidationContext.setSubProcessConfigIdSet(subProcessConfigIdSet);
        }
        return subProcessConfigIdSet;
    }

}
