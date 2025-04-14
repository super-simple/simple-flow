package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.ArrayUtils;
import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.MultiMapUtils;
import org.ss.simpleflow.core.component.SfComponentConfig;
import org.ss.simpleflow.core.context.SfValidationProcessContext;
import org.ss.simpleflow.core.context.SfValidationWholeContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.util.StackUtils;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.node.SfNodeParameter;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessPreprocessConfig;
import org.ss.simpleflow.core.validate.SfOrphanComponentCleaner;

import java.util.*;

public class SfDefaultOrphanComponentCleaner<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>>
        implements SfOrphanComponentCleaner<NI, EI, PCI,
        NC, EC, PC> {

    @Override
    public void cleanOrphanComponent(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                                     SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext,
                                     SfProcessPreprocessConfig processPreprocessConfig) {
        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        cleanOrphanNodeAndEdge(mainProcessConfig,
                               validationWholeContext.getMainValidationProcessContext(),
                               processPreprocessConfig);

        PC[] subProcessConfigArray = wholeProcessConfig.getSubProcessConfigArray();
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] subValidationProcessContextArray = validationWholeContext.getSubValidationProcessContextArray();
        int length = subProcessConfigArray.length;
        for (int i = 0; i < length; i++) {
            PC subProcessConfig = subProcessConfigArray[i];
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> subValidationProcessContext = subValidationProcessContextArray[i];
            cleanOrphanNodeAndEdge(subProcessConfig, subValidationProcessContext, processPreprocessConfig);
        }
    }

    private void cleanOrphanNodeAndEdge(PC processConfig,
                                        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> processValidationContext,
                                        SfProcessPreprocessConfig processPreprocessConfig) {
        EC[] edgeConfigArray = processConfig.getEdgeConfigArray();
        NC[] nodeConfigArray = processConfig.getNodeConfigArray();

        int controlEdgeCount = processValidationContext.getControlEdgeCount();
        List<EC> controlEdgeList = CollectionUtils.collect(edgeConfigArray,
                                                           SfAbstractEdgeConfig::isControlEdge,
                                                           controlEdgeCount);
        Map<NI, List<EC>> outgoingControlEdgeMap = MultiMapUtils.index(controlEdgeList,
                                                                       SfAbstractEdgeConfig::getFromNodeId);

        Set<NI> visitedNodeSet = new HashSet<>();
        Deque<SfComponentConfig> stack = new ArrayDeque<>();

        stack.push(processValidationContext.getStartNodeConfig());
        Map<NI, NC> nodeConfigMap = processValidationContext.getNodeConfigMap();
        while (!stack.isEmpty()) {
            SfComponentConfig current = stack.pop();
            if (current instanceof SfAbstractNodeConfig) {
                @SuppressWarnings("unchecked") NC nodeConfig = (NC) current;
                NI nodeId = nodeConfig.getId();
                if (!visitedNodeSet.contains(nodeId)) {
                    visitedNodeSet.add(nodeId);

                    if (nodeConfig.isSubProcessNode()) {
                        Set<PCI> subProcessConfigIdSet = SfDefaultBasicValidator.getSubProcessConfigIdSet(
                                processValidationContext);
                        subProcessConfigIdSet.add(nodeConfig.getProcessId());
                    }

                    StackUtils.pushAllToStack(stack, outgoingControlEdgeMap.get(nodeId));
                }
            } else {
                @SuppressWarnings("unchecked") EC edgeConfig = (EC) current;
                stack.push(nodeConfigMap.get(edgeConfig.getToNodeId()));
            }
        }

        NC[] newNodeConfigArray = removeOrphanNode(nodeConfigArray, visitedNodeSet, processConfig);
        processConfig.setNodeConfigArray(newNodeConfigArray);

        int length = edgeConfigArray.length;
        if (length > 0) {
            Map<NI, Set<Integer>> parameterKeySetMap = new HashMap<>();
            EC[] newEdgeConfigArray = removeOrphanEdge(edgeConfigArray, visitedNodeSet, processConfig);
            processConfig.setEdgeConfigArray(newEdgeConfigArray);

            for (EC ec : newEdgeConfigArray) {
                if (ec.isDataEdge()) {
                    NI fromNodeId = ec.getFromNodeId();
                    Set<Integer> parameterKeySet = parameterKeySetMap.computeIfAbsent(fromNodeId, k -> new HashSet<>());
                    parameterKeySet.add(ec.getFromResultIndex());
                }
            }

            for (NC nodeConfig : nodeConfigArray) {
                SfNodeParameter[] parameter = nodeConfig.getParameter();
                if (ArrayUtils.isNotEmpty(parameter)) {
                    Set<Integer> parameterIndexSet = parameterKeySetMap.get(nodeConfig.getId());
                    if (CollectionUtils.isNullOrEmpty(parameterIndexSet)) {
                        nodeConfig.setParameter(null);
                    } else {
                        int[] indexArray = new int[parameter.length];
                        for (Integer parameterIndex : parameterIndexSet) {
                            indexArray[parameterIndex] = 1;
                        }
                        for (int i = 0; i < parameter.length; i++) {
                            if (indexArray[i] == 0) {
                                parameter[i] = null;
                            }
                        }
                    }
                }
            }
        }
    }

    private NC[] removeOrphanNode(NC[] nodeConfigArray, Set<NI> visitedNodeSet, PC pc) {
        int newLength = 0;
        for (NC nc : nodeConfigArray) {
            if (visitedNodeSet.contains(nc.getId())) {
                newLength++;
            }
        }
        NC[] newNodeConfigArray = pc.createNodeConfigArray(newLength);
        int index = 0;
        for (NC nc : nodeConfigArray) {
            if (visitedNodeSet.contains(nc.getId())) {
                newNodeConfigArray[index++] = nc;
            }
        }
        return newNodeConfigArray;
    }

    private EC[] removeOrphanEdge(EC[] edgeConfigArray, Set<NI> visitedNodeSet, PC pc) {
        int newLength = 0;
        for (EC ec : edgeConfigArray) {
            if (visitedNodeSet.contains(ec.getFromNodeId()) || visitedNodeSet.contains(ec.getToNodeId())) {
                newLength++;
            }
        }
        EC[] newEdgeConfigArray = pc.createEdgeConfigArray(newLength);
        int index = 0;
        for (EC ec : edgeConfigArray) {
            if (visitedNodeSet.contains(ec.getId())) {
                newEdgeConfigArray[index++] = ec;
            }
        }
        return newEdgeConfigArray;
    }

}
