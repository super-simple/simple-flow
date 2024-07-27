package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.MapUtils;
import org.ss.simpleflow.common.MultiMapUtils;
import org.ss.simpleflow.core.component.SfComponentConfig;
import org.ss.simpleflow.core.context.SfExecutionGlobalContext;
import org.ss.simpleflow.core.context.SfExecutionProcessContext;
import org.ss.simpleflow.core.context.SfExecutionProcessInternalContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.util.StackUtils;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.node.SfNodeParameter;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfOrphanComponentCleaner;

import java.util.*;

public class SfDefaultOrphanComponentCleaner<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI>
        implements SfOrphanComponentCleaner<NI, EI, PCI,
        NC, EC,
        PCG, PC,
        NEI, EEI, PEI> {

    @Override
    public void cleanOrphanComponent(PC processConfig,
                                     SfProcessContext<NI, EI, PCI,
                                             NC, EC,
                                             PCG, PC, PEI> processContext,
                                     SfExecutionGlobalContext<NI, EI, PCI,
                                             NC, EC,
                                             PCG, PC,
                                             NEI, EEI, PEI> executionGlobalContext,
                                     SfProcessEngineConfig processEngineConfig) {
        List<NC> nodeConfigList = processConfig.getNodeConfigList();
        List<EC> edgeConfigList = processConfig.getEdgeConfigList();
        cleanOrphanNodeAndEdge(nodeConfigList,
                               edgeConfigList,
                               processConfig,
                               null,
                               processContext,
                               executionGlobalContext.getMainExecutionProcessContext(),
                               processEngineConfig);

        List<PCG> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList = executionGlobalContext.getSubExecutionProcessContextList();
            int size = subProcessConfigList.size();
            for (int i = 0; i < size; i++) {
                PCG subProcessConfig = subProcessConfigList.get(i);
                SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> subExecutionProcessContext = subExecutionProcessContextList.get(
                        i);
                List<NC> subNodeConfigList = subProcessConfig.getNodeConfigList();
                List<EC> subEdgeConfigList = subProcessConfig.getEdgeConfigList();
                cleanOrphanNodeAndEdge(subNodeConfigList,
                                       subEdgeConfigList,
                                       processConfig,
                                       subProcessConfig,
                                       processContext,
                                       executionGlobalContext.getMainExecutionProcessContext(), processEngineConfig);
            }
        }
    }

    private void cleanOrphanNodeAndEdge(List<NC> nodeConfigList,
                                        List<EC> edgeConfigList,
                                        PC processConfig,
                                        PCG processConfigGraph,
                                        SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
                                        SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionProcessContext,
                                        SfProcessEngineConfig processEngineConfig) {

        List<EC> controlEdgeList = CollectionUtils.collect(edgeConfigList,
                                                           SfAbstractEdgeConfig::isControlEdge);
        Map<NI, List<EC>> outgoingControlEdgeMap = MultiMapUtils.index(controlEdgeList,
                                                                       SfAbstractEdgeConfig::getFromNodeId);

        Set<NI> visitedNodeSet = new HashSet<>();
        Deque<SfComponentConfig> stack = new ArrayDeque<>();

        SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionInternalContext = executionProcessContext.getExecutionInternalContext();
        stack.push(executionInternalContext.getStartNodeConfig());
        Map<NI, NC> nodeConfigMap = executionInternalContext.getNodeConfigMap();
        while (!stack.isEmpty()) {
            SfComponentConfig current = stack.pop();
            if (current instanceof SfAbstractNodeConfig) {
                @SuppressWarnings("unchecked") NC nodeConfig = (NC) current;
                NI nodeId = nodeConfig.getId();
                if (!visitedNodeSet.contains(nodeId)) {
                    visitedNodeSet.add(nodeId);

                    StackUtils.pushAllToStack(stack, outgoingControlEdgeMap.get(nodeId));
                }
            } else {
                @SuppressWarnings("unchecked") EC edgeConfig = (EC) current;
                stack.push(nodeConfigMap.get(edgeConfig.getToNodeId()));
            }
        }

        nodeConfigList.removeIf(nodeConfig -> !visitedNodeSet.contains(nodeConfig.getId()));
        if (CollectionUtils.isNotEmpty(edgeConfigList)) {

            Map<NI, Set<String>> parameterKeySetMap = new HashMap<>();
            Iterator<EC> edgeConfigListIterator = edgeConfigList.iterator();
            while (edgeConfigListIterator.hasNext()) {
                EC next = edgeConfigListIterator.next();
                if (!visitedNodeSet.contains(next.getFromNodeId()) || !visitedNodeSet.contains(next.getToNodeId())) {
                    edgeConfigListIterator.remove();
                }
                if (next.isDataEdge()) {
                    NI fromNodeId = next.getFromNodeId();
                    Set<String> parameterKeySet = parameterKeySetMap.computeIfAbsent(fromNodeId, k -> new HashSet<>());
                    parameterKeySet.add(next.getFromResultKey());
                }
            }

            for (NC nodeConfig : nodeConfigList) {
                Map<String, SfNodeParameter> parameterMap = nodeConfig.getParameterMap();
                if (MapUtils.isNotEmpty(parameterMap)) {
                    Set<String> parameterKeySet = parameterKeySetMap.get(nodeConfig.getId());
                    if (CollectionUtils.isNullOrEmpty(parameterKeySet)) {
                        nodeConfig.setParameterMap(null);
                    } else {
                        parameterMap.keySet().removeIf(parameterKey -> !parameterKeySet.contains(parameterKey));
                    }
                }
            }
        }
    }

}
