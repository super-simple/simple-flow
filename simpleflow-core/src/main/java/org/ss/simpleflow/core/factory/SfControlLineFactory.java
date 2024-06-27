package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfLineContext;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.line.SfControlLine;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfControlLineFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        LINE_EXECUTION_ID> {

    SfControlLine<NODE_ID, LINE_ID, LINE_EXECUTION_ID, LINE_CONFIG>
    createControlLine(NODE_CONFIG controlLineConfig,
                      PROCESS_CONFIG_GRAPH processConfigGraph,
                      SfLineContext<NODE_ID, LINE_ID, LINE_EXECUTION_ID, LINE_CONFIG> lineContext);

}
