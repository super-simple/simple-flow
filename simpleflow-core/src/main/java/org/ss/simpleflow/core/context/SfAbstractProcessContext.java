package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public abstract class SfAbstractProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>,
        PROCESS_EXECUTION_ID>
        extends SfDefaultVariableContext
        implements SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG,
        LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> {

    protected PROCESS_EXECUTION_ID processExecutionId;

    protected PROCESS_CONFIG processConfig;

    @Override
    public void setExecutionId(PROCESS_EXECUTION_ID processExecutionId) {
        this.processExecutionId = processExecutionId;
    }

    @Override
    public PROCESS_EXECUTION_ID getExecutionId() {
        return processExecutionId;
    }

    @Override
    public void setProcessConfig(PROCESS_CONFIG processConfig) {
        this.processConfig = processConfig;
    }

    @Override
    public PROCESS_CONFIG getProcessConfig() {
        return processConfig;
    }

}
