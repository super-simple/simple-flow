package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.MapUtils;
import org.ss.simpleflow.common.MultiMapUtils;
import org.ss.simpleflow.core.component.SfComponentConfig;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.util.StackUtils;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.node.SfNodeParameter;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfOrphanComponentCleaner;

import java.util.*;

public class SfDefaultOrphanComponentCleaner<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>, EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>, PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>, PROCESS_CONFIG extends SfProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>, PROCESS_EXECUTION_ID> implements SfOrphanComponentCleaner<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> {

    @Override
    public void cleanOrphanComponent(PROCESS_CONFIG origin) {
    }


    private void cleanOrphanNodeAndEdge(List<NODE_CONFIG> nodeConfigList,
                                        List<EDGE_CONFIG> edgeConfigList,
                                        PROCESS_CONFIG processConfig,
                                        PROCESS_CONFIG_GRAPH processConfigGraph,
                                        SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                                        SfProcessEngineConfig processEngineConfig) {
        Map<NODE_ID, NODE_CONFIG> nodeConfigMap = MapUtils.uniqueIndex(nodeConfigList, SfAbstractNodeConfig::getId);

        List<EDGE_CONFIG> controlEdgeList = CollectionUtils.collect(edgeConfigList,
                                                                    SfAbstractEdgeConfig::isControlEdge);
        Map<NODE_ID, List<EDGE_CONFIG>> outgoingControlEdgeMap = MultiMapUtils.index(controlEdgeList,
                                                                                     SfAbstractEdgeConfig::getFromNodeId);

        Set<NODE_ID> visitedNodeSet = new HashSet<>();
        Deque<SfComponentConfig> stack = new ArrayDeque<>();

        NODE_CONFIG startNodeConfig = CollectionUtils.find(nodeConfigList, SfAbstractNodeConfig::isStartNode);
        stack.push(startNodeConfig);
        while (!stack.isEmpty()) {
            SfComponentConfig current = stack.pop();
            if (current instanceof SfAbstractNodeConfig) {
                @SuppressWarnings("unchecked") NODE_CONFIG nodeConfig = (NODE_CONFIG) current;
                NODE_ID nodeId = nodeConfig.getId();
                if (!visitedNodeSet.contains(nodeId)) {
                    visitedNodeSet.add(nodeId);

                    StackUtils.pushToStack(stack, outgoingControlEdgeMap.get(nodeId));
                }
            } else {
                @SuppressWarnings("unchecked") EDGE_CONFIG edgeConfig = (EDGE_CONFIG) current;
                stack.push(nodeConfigMap.get(edgeConfig.getToNodeId()));
            }
        }

        nodeConfigList.removeIf(nodeConfig -> !visitedNodeSet.contains(nodeConfig.getId()));
        if (CollectionUtils.isNotEmpty(edgeConfigList)) {

            Map<NODE_ID, Set<String>> parameterKeySetMap = new HashMap<>();
            Iterator<EDGE_CONFIG> edgeConfigListIterator = edgeConfigList.iterator();
            while (edgeConfigListIterator.hasNext()) {
                EDGE_CONFIG next = edgeConfigListIterator.next();
                if (!visitedNodeSet.contains(next.getFromNodeId()) || !visitedNodeSet.contains(next.getToNodeId())) {
                    edgeConfigListIterator.remove();
                }
                if (next.isDataEdge()) {
                    NODE_ID fromNodeId = next.getFromNodeId();
                    Set<String> parameterKeySet = parameterKeySetMap.computeIfAbsent(fromNodeId,
                                                                                     k -> new HashSet<>());
                    parameterKeySet.add(next.getFromResultKey());
                }
            }

            for (NODE_CONFIG nodeConfig : nodeConfigList) {
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
