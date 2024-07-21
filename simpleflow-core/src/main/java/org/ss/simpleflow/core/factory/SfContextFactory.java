package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.*;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfContextFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    SfLineContext<NODE_ID, EDGE_ID, EDGE_EXECUTION_ID, EDGE_CONFIG> createLineContext();

    SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID, NODE_CONFIG> createNodeContext();

    SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG,
            PROCESS_EXECUTION_ID> createProcessContext();

    SfExecutionContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG,
            NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> createExecutionContext();

    SfExecutionExternalContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG,
            NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> createExecutionExternalContext();

    SfExecutionInternalContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG,
            NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> createExecutionInternalContext();

    SfProcessReturn<PROCESS_EXECUTION_ID> createProcessReturn();
}
