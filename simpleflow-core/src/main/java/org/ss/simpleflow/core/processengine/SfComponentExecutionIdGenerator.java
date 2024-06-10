package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

public interface SfComponentExecutionIdGenerator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>> {

    String generateNodeExecutionId(NODE_CONFIG nodeConfig);

    String generateLineExecutionId(LINE_CONFIG lineConfig);
}
