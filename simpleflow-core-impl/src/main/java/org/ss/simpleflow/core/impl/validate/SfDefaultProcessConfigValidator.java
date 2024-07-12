package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public class SfDefaultProcessConfigValidator
        <NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
                LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
                PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>> {
    public void validateSingleProcessConfig(PROCESS_CONFIG_GRAPH processConfigGraph) {

    }

}
