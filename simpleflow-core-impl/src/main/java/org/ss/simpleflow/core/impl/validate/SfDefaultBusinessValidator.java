package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.IntList;
import org.ss.simpleflow.core.context.SfValidationProcessContext;
import org.ss.simpleflow.core.context.SfValidationWholeContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigExceptionCode;
import org.ss.simpleflow.core.impl.util.StackUtils;
import org.ss.simpleflow.core.index.SfIndexEntry;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

import java.util.*;

public class SfDefaultBusinessValidator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    public void businessValidate(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                                 SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext,
                                 SfProcessEngineConfig processEngineConfig) {

        validateProcessCircularReference(validationWholeContext,
                                         processEngineConfig);

        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();

        validateProcess(mainProcessConfig,
                        validationWholeContext.getMainValidationProcessContext(),
                        processEngineConfig);

        PC[] subProcessConfigArray = wholeProcessConfig.getSubProcessConfigArray();
        int length = subProcessConfigArray.length;
        if (length > 0) {
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] subValidationProcessContextArray = validationWholeContext.getSubValidationProcessContextArray();
            for (int i = 0; i < length; i++) {
                PC subProcessConfig = subProcessConfigArray[i];
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> validationProcessContext = subValidationProcessContextArray[i];
                validateProcess(subProcessConfig, validationProcessContext, processEngineConfig);
            }
        }
    }


    private void validateProcess(PC processConfig,
                                 SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> validationProcessContext,
                                 SfProcessEngineConfig processEngineConfig) {
        NC[] nodeConfigArray = processConfig.getNodeConfigArray();
        EC[] edgeConfigArray = processConfig.getEdgeConfigArray();

        generateIndex(nodeConfigArray, edgeConfigArray, validationProcessContext);

        int nodeConfigListLength = nodeConfigArray.length;
        List<SfIndexEntry> controlLineList = validationProcessContext.getControlEdgeIndexEntryList();
        List<List<SfIndexEntry>> allOutgoingControlEdgeList = new ArrayList<>(nodeConfigListLength);
        for (int i = 0; i < nodeConfigListLength; i++) {
            allOutgoingControlEdgeList.add(new ArrayList<>());
        }
        for (SfIndexEntry edgeIndexEntry : controlLineList) {
            int fromNodeConfigIndex = edgeIndexEntry.getFromNodeConfigIndex();
            List<SfIndexEntry> outgoingControlEdgeList = allOutgoingControlEdgeList.get(fromNodeConfigIndex);
            outgoingControlEdgeList.add(edgeIndexEntry);
        }

        validationProcessContext.setAllOutgoingControlEdgeList(allOutgoingControlEdgeList);

        computeLoopExpansion(validationProcessContext);
    }

    private void computeLoopExpansion(
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> validationProcessContext) {
        List<SfIndexEntry> nodeIndexEntryList = validationProcessContext.getNodeIndexEntryList();
        int nodeConfigListSize = nodeIndexEntryList.size();

        List<List<SfIndexEntry>> allOutgoingControlEdgeList = validationProcessContext.getAllOutgoingControlEdgeList();

        Deque<SfIndexEntry> stack = new ArrayDeque<>();
        int startNodeConfigIndex = validationProcessContext.getStartNodeConfigIndex();
        stack.push(nodeIndexEntryList.get(startNodeConfigIndex));

        byte[] visitedNodeArray = new byte[nodeConfigListSize];
        IntList loopStartNodeList = new IntList();
        while (!stack.isEmpty()) {
            SfIndexEntry current = stack.pop();
            if (current.isNode()) {
                int selfIndex = current.getSelfIndex();
                byte visited = visitedNodeArray[selfIndex];
                if (visited == 0) {
                    visitedNodeArray[selfIndex] = 1;
                    StackUtils.pushAllToStack(stack, allOutgoingControlEdgeList.get(selfIndex));
                } else {
                    loopStartNodeList.add(selfIndex);
                }
            } else {
                stack.push(nodeIndexEntryList.get(current.getToNodeConfigIndex()));
            }
        }
    }

    private void generateIndex(
            NC[] nodeConfigArray,
            EC[] edgeConfigArray,
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> validationProcessContext) {
        int nodeConfigArrayLength = nodeConfigArray.length;
        List<SfIndexEntry> nodeIndexEntryList = new ArrayList<>(nodeConfigArrayLength);
        Map<NI, Integer> nodeConfigIndexMap = new HashMap<>(nodeConfigArrayLength);
        for (int i = 0; i < nodeConfigArrayLength; i++) {
            SfIndexEntry nodeIndexEntry = new SfIndexEntry();
            nodeIndexEntry.setIndexTypeNode();
            nodeIndexEntry.setSelfIndex(i);
            nodeIndexEntryList.add(nodeIndexEntry);
            NC nodeConfig = nodeConfigArray[i];
            if (nodeConfig.isStartNode()) {
                validationProcessContext.setStartNodeConfigIndex(i);
            }
            nodeConfigIndexMap.put(nodeConfig.getId(), i);
        }

        validationProcessContext.setNodeIndexEntryList(nodeIndexEntryList);

        int edgeConfigListLength = edgeConfigArray.length;
        if (edgeConfigListLength == 0) {
            return;
        }

        List<SfIndexEntry> edgeIndexEntryList = new ArrayList<>(edgeConfigListLength);
        List<SfIndexEntry> controlEdgeIndexEntryList = new ArrayList<>(validationProcessContext.getControlEdgeCount());
        for (int i = 0; i < edgeConfigListLength; i++) {
            EC edgeConfig = edgeConfigArray[i];
            SfIndexEntry edgeIndexEntry = new SfIndexEntry();
            edgeIndexEntry.setIndexTypeEdge();

            edgeIndexEntryList.add(edgeIndexEntry);
            if (edgeConfig.isControlEdge()) {
                controlEdgeIndexEntryList.add(edgeIndexEntry);
            }

            edgeIndexEntry.setEdgeTypeIndexControl();
            edgeIndexEntry.setSelfIndex(i);

            NI fromNodeId = edgeConfig.getFromNodeId();
            NI toNodeId = edgeConfig.getToNodeId();

            edgeIndexEntry.setFromNodeConfigIndex(nodeConfigIndexMap.get(fromNodeId));
            edgeIndexEntry.setToNodeConfigIndex(nodeConfigIndexMap.get(toNodeId));
        }

        validationProcessContext.setEdgeIndexEntryList(edgeIndexEntryList);
        validationProcessContext.setControlEdgeIndexEntryList(controlEdgeIndexEntryList);
    }

    private void validateProcessCircularReference(
            SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext,
            SfProcessEngineConfig processEngineConfig) {
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext = validationWholeContext.getMainValidationProcessContext();
        Set<PCI> mainSubProcessConfigIdSet = mainProcessValidationContext.getSubProcessConfigIdSet();
        if (CollectionUtils.isNotEmpty(mainSubProcessConfigIdSet)) {
            Map<PCI, Set<PCI>> subProcessContainProcessConfigIdMap = validationWholeContext.getSubProcessContainProcessConfigIdMap();

            for (Map.Entry<PCI, Set<PCI>> entry : subProcessContainProcessConfigIdMap.entrySet()) {
                PCI processConfigId = entry.getKey();
                Set<PCI> referencedProcessConfigIdSet = entry.getValue();
                if (CollectionUtils.isNotEmpty(referencedProcessConfigIdSet)) {
                    for (PCI referencedProcessConfigId : referencedProcessConfigIdSet) {
                        Set<PCI> processConfigIdSet = subProcessContainProcessConfigIdMap.get(
                                referencedProcessConfigId);
                        if (CollectionUtils.isNotEmpty(processConfigIdSet)) {
                            if (processConfigIdSet.contains(processConfigId)) {
                                throw new SfProcessConfigException("process[" + processConfigId + "] and [" +
                                                                           referencedProcessConfigId + "] exist circular reference",
                                                                   SfProcessConfigExceptionCode.CIRCULAR_REFERENCE,
                                                                   null,
                                                                   processEngineConfig);
                            }
                        }
                    }
                }
            }
        }
    }

}
