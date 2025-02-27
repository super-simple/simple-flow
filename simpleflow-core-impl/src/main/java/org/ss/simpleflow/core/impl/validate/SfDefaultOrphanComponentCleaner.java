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
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
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
                                     SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationGlobalContext,
                                     SfProcessEngineConfig processEngineConfig) {
        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        cleanOrphanNodeAndEdge(mainProcessConfig,
                               validationGlobalContext.getMainProcessValidationContext(),
                               processEngineConfig);

        List<PC> subProcessConfigList = wholeProcessConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>> subValidationProcessContextList = validationGlobalContext.getSubValidationProcessContextList();
            int size = subProcessConfigList.size();
            for (int i = 0; i < size; i++) {
                PC subProcessConfig = subProcessConfigList.get(i);
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> subValidationProcessContext = subValidationProcessContextList.get(
                        i);
                cleanOrphanNodeAndEdge(subProcessConfig, subValidationProcessContext, processEngineConfig);
            }
        }
    }

    private void cleanOrphanNodeAndEdge(PC processConfig,
                                        SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> processValidationContext,
                                        SfProcessEngineConfig processEngineConfig) {
        List<EC> edgeConfigList = processConfig.getEdgeConfigList();
        List<NC> nodeConfigList = processConfig.getNodeConfigList();
        if (CollectionUtils.isNullOrEmpty(edgeConfigList)) {
            Iterator<NC> iterator = nodeConfigList.iterator();
            if (iterator.hasNext()) {
                NC next = iterator.next();
                if (!next.isStartNode()) {
                    iterator.remove();
                }
            }
            return;
        }

        List<EC> controlEdgeList = CollectionUtils.collect(edgeConfigList,
                                                           SfAbstractEdgeConfig::isControlEdge,
                                                           edgeConfigList.size() / 2);
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

        nodeConfigList.removeIf(nodeConfig -> !visitedNodeSet.contains(nodeConfig.getId()));
        if (CollectionUtils.isNotEmpty(edgeConfigList)) {

            Map<NI, Set<Integer>> parameterKeySetMap = new HashMap<>();
            Iterator<EC> edgeConfigListIterator = edgeConfigList.iterator();
            while (edgeConfigListIterator.hasNext()) {
                EC next = edgeConfigListIterator.next();
                if (!visitedNodeSet.contains(next.getFromNodeId()) || !visitedNodeSet.contains(next.getToNodeId())) {
                    edgeConfigListIterator.remove();
                }
                if (next.isDataEdge()) {
                    NI fromNodeId = next.getFromNodeId();
                    Set<Integer> parameterKeySet = parameterKeySetMap.computeIfAbsent(fromNodeId, k -> new HashSet<>());
                    parameterKeySet.add(next.getFromResultIndex());
                }
            }

            for (NC nodeConfig : nodeConfigList) {
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

}
