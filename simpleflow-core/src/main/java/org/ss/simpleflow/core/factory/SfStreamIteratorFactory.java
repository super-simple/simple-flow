package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.iterator.SfStreamIterator;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfStreamIteratorFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>> {

    SfStreamIterator<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG>
    createStreamIterator(NODE_CONFIG streamIteratorConfig,
                         PROCESS_CONFIG_GRAPH processConfigGraph,
                         SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG> nodeContext);

}
