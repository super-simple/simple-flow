package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.io.Serializable;

public interface SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>,
        PROCESS_EXECUTION_ID> extends SfVariableContext, Serializable {

    void setParentProcessContext(SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, LINE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext);

    SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, LINE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> getParentProcessContext();

    void setProcessExecutionId(PROCESS_EXECUTION_ID processExecutionId);

    PROCESS_EXECUTION_ID getProcessExecutionId();

    void setProcessConfig(PROCESS_CONFIG processConfig);

    PROCESS_CONFIG getProcessConfig();

    void setProcessConfigId(PROCESS_CONFIG_ID processConfigId);

    PROCESS_CONFIG_ID getProcessConfigId();
}
