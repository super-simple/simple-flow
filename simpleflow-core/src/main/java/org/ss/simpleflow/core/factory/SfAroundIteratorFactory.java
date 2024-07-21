package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.iterator.SfAroundIterator;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfAroundIteratorFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    SfAroundIterator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG,
            EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID>
    createAroundIterator(
            SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID, NODE_CONFIG> nodeContext,
            SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG,
                    EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext);
}
