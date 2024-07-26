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

public class SfDefaultOrphanComponentCleaner<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfAbstractProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>
        implements SfOrphanComponentCleaner<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG, EDGE_CONFIG,
        PROCESS_CONFIG_GRAPH, PROCESS_CONFIG,
        NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    @Override
    public void cleanOrphanComponent(PROCESS_CONFIG processConfig,
                                     SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
                                             NODE_CONFIG, EDGE_CONFIG,
                                             PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                                     SfExecutionGlobalContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
                                             NODE_CONFIG, EDGE_CONFIG,
                                             PROCESS_CONFIG_GRAPH, PROCESS_CONFIG,
                                             NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> executionGlobalContext,
                                     SfProcessEngineConfig processEngineConfig) {
        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
        List<EDGE_CONFIG> edgeConfigList = processConfig.getEdgeConfigList();
        cleanOrphanNodeAndEdge(nodeConfigList,
                               edgeConfigList,
                               processConfig,
                               null,
                               processContext,
                               executionGlobalContext.getMainExecutionProcessContext(),
                               processEngineConfig);

        List<PROCESS_CONFIG_GRAPH> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            List<SfExecutionProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>> subExecutionProcessContextList = executionGlobalContext.getSubExecutionProcessContextList();
            int size = subProcessConfigList.size();
            for (int i = 0; i < size; i++) {
                PROCESS_CONFIG_GRAPH subProcessConfig = subProcessConfigList.get(i);
                SfExecutionProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> subExecutionProcessContext = subExecutionProcessContextList.get(
                        i);
                List<NODE_CONFIG> subNodeConfigList = subProcessConfig.getNodeConfigList();
                List<EDGE_CONFIG> subEdgeConfigList = subProcessConfig.getEdgeConfigList();
                cleanOrphanNodeAndEdge(subNodeConfigList,
                                       subEdgeConfigList,
                                       processConfig,
                                       subProcessConfig,
                                       processContext,
                                       executionGlobalContext.getMainExecutionProcessContext(), processEngineConfig);
            }
        }
    }

    private void cleanOrphanNodeAndEdge(List<NODE_CONFIG> nodeConfigList,
                                        List<EDGE_CONFIG> edgeConfigList,
                                        PROCESS_CONFIG processConfig,
                                        PROCESS_CONFIG_GRAPH processConfigGraph,
                                        SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                                        SfExecutionProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> executionProcessContext,
                                        SfProcessEngineConfig processEngineConfig) {

        List<EDGE_CONFIG> controlEdgeList = CollectionUtils.collect(edgeConfigList,
                                                                    SfAbstractEdgeConfig::isControlEdge);
        Map<NODE_ID, List<EDGE_CONFIG>> outgoingControlEdgeMap = MultiMapUtils.index(controlEdgeList,
                                                                                     SfAbstractEdgeConfig::getFromNodeId);

        Set<NODE_ID> visitedNodeSet = new HashSet<>();
        Deque<SfComponentConfig> stack = new ArrayDeque<>();

        SfExecutionProcessInternalContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> executionInternalContext = executionProcessContext.getExecutionInternalContext();
        stack.push(executionInternalContext.getStartNodeConfig());
        Map<NODE_ID, NODE_CONFIG> nodeConfigMap = executionInternalContext.getNodeConfigMap();
        while (!stack.isEmpty()) {
            SfComponentConfig current = stack.pop();
            if (current instanceof SfAbstractNodeConfig) {
                @SuppressWarnings("unchecked") NODE_CONFIG nodeConfig = (NODE_CONFIG) current;
                NODE_ID nodeId = nodeConfig.getId();
                if (!visitedNodeSet.contains(nodeId)) {
                    visitedNodeSet.add(nodeId);

                    StackUtils.pushAllToStack(stack, outgoingControlEdgeMap.get(nodeId));
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
                    Set<String> parameterKeySet = parameterKeySetMap.computeIfAbsent(fromNodeId, k -> new HashSet<>());
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
