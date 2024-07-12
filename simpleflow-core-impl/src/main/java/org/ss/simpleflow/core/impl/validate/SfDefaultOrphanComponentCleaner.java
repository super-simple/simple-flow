package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.validate.SfOrphanComponentCleaner;

import java.util.List;

public class SfDefaultOrphanComponentCleaner<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>>
        implements SfOrphanComponentCleaner<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG, LINE_CONFIG,
        PROCESS_CONFIG_GRAPH, PROCESS_CONFIG> {
    @Override
    public void cleanOrphanProcess(PROCESS_CONFIG origin) {
        List<NODE_CONFIG> nodeConfigList = origin.getNodeConfigList();
    }

    @Override
    public void cleanOrphanNode(PROCESS_CONFIG origin) {
    }

}
