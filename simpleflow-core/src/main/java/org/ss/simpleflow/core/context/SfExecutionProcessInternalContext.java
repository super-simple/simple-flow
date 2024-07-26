package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.Map;
import java.util.Set;

public abstract class SfExecutionProcessInternalContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfAbstractProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    private NODE_CONFIG startNodeConfig;

    private Map<NODE_ID, NODE_CONFIG> nodeConfigMap;

    private Set<PROCESS_CONFIG_ID> subProcessConfigIdSet;

    public NODE_CONFIG getStartNodeConfig() {
        return startNodeConfig;
    }

    public void setStartNodeConfig(NODE_CONFIG startNodeConfig) {
        this.startNodeConfig = startNodeConfig;
    }

    public Map<NODE_ID, NODE_CONFIG> getNodeConfigMap() {
        return nodeConfigMap;
    }

    public void setNodeConfigMap(Map<NODE_ID, NODE_CONFIG> nodeConfigMap) {
        this.nodeConfigMap = nodeConfigMap;
    }

    public Set<PROCESS_CONFIG_ID> getSubProcessConfigIdSet() {
        return subProcessConfigIdSet;
    }

    public void setSubProcessConfigIdSet(Set<PROCESS_CONFIG_ID> subProcessConfigIdSet) {
        this.subProcessConfigIdSet = subProcessConfigIdSet;
    }
}
