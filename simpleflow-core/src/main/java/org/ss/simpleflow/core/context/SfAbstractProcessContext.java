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

    protected SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, LINE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext;
    protected PROCESS_EXECUTION_ID processExecutionId;

    protected PROCESS_CONFIG processConfig;

    @Override
    public void setParentProcessContext(SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, LINE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext) {
        this.processContext = processContext;
    }

    @Override
    public SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, LINE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> getParentProcessContext() {
        return processContext;
    }

    @Override
    public void setProcessExecutionId(PROCESS_EXECUTION_ID processExecutionId) {
        this.processExecutionId = processExecutionId;
    }

    @Override
    public PROCESS_EXECUTION_ID getProcessExecutionId() {
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
