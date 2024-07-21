package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.context.SfProcessReturn;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.Map;

public interface SfProcessEngine<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    SfProcessReturn<PROCESS_EXECUTION_ID> runProcess(PROCESS_CONFIG processConfig,
                                                     PROCESS_EXECUTION_ID executionId,
                                                     Map<String, Object> params,
                                                     Map<String, Object> env);

    SfProcessReturn<PROCESS_EXECUTION_ID> runProcess(PROCESS_CONFIG processConfig,
                                                     Map<String, Object> params,
                                                     Map<String, Object> env);

    SfProcessReturn<PROCESS_EXECUTION_ID> runProcess(PROCESS_CONFIG processConfig, Map<String, Object> params);

}
