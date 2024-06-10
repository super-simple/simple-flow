package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfControlLineContext;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.line.SfDataLine;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfDataLineFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>> {

    SfDataLine<NODE_ID, LINE_ID, LINE_CONFIG>
    createDataLine(LINE_CONFIG dataLineConfig,
                   PROCESS_CONFIG_GRAPH processConfigGraph,
                   SfControlLineContext<NODE_ID, LINE_ID, LINE_CONFIG> lineContext);

}
