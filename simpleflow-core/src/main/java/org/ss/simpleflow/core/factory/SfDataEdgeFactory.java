package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.edge.SfDataEdge;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfDataEdgeFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfAbstractProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    SfDataEdge<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG,
            EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>
    createDataEdge(
            SfEdgeContext<NODE_ID, EDGE_ID, EDGE_EXECUTION_ID, EDGE_CONFIG> edgeContext,
            SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG,
                    EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext);

}
