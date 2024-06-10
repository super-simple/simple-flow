package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.Map;

public interface SfProcessEngine<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>> {

    String runProcess(PROCESS_CONFIG processConfig,
                      String executionId,
                      Map<String, Object> params,
                      Map<String, Object> env);

    String runProcess(PROCESS_CONFIG processConfig, Map<String, Object> params, Map<String, Object> env);

    String runProcess(PROCESS_CONFIG processConfig, Map<String, Object> params);

}
