package org.ss.simpleflow.core.aspect;

import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.Map;

public interface SfProcessAspect<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>,
        PROCESS_EXECUTION_ID> {

    void before(Map<String, Object> params,
                SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                        NODE_CONFIG, LINE_CONFIG,
                        PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext);

    void afterThrowing(Exception e,
                       SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                               NODE_CONFIG, LINE_CONFIG,
                               PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext);

    void afterReturning(Map<String, Object> result,
                        SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                                NODE_CONFIG, LINE_CONFIG,
                                PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext);

    void afterFinally(SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, LINE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext);

    void afterCancel(SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, LINE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext);

}
