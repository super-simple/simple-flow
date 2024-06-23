package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.context.SfControlLineContext;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfComponentExecutionIdGenerator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        NODE_EXECUTION_ID, LINE_EXECUTION_ID> {

    NODE_EXECUTION_ID generateNodeExecutionId(NODE_CONFIG nodeConfig,
                                              PROCESS_CONFIG_GRAPH processConfigGraph,
                                              SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG> nodeContext);

    LINE_EXECUTION_ID generateLineExecutionId(LINE_CONFIG dataLineConfig,
                                              PROCESS_CONFIG_GRAPH processConfigGraph,
                                              SfControlLineContext<NODE_ID, LINE_ID, LINE_CONFIG> lineContext);
}
