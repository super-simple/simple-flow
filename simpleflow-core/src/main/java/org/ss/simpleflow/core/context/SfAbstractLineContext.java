package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;

public abstract class SfAbstractLineContext<NODE_ID, LINE_ID, LINE_EXECUTION_ID, LINE_CONFIG
        extends SfAbstractLineConfig<LINE_ID, NODE_ID>>
        extends SfDefaultVariableContext
        implements SfLineContext<NODE_ID, LINE_ID, LINE_EXECUTION_ID, LINE_CONFIG> {

    protected LINE_EXECUTION_ID lineExecutionId;

    protected LINE_CONFIG lineConfig;

    @Override
    public void setExecutionId(LINE_EXECUTION_ID lineExecutionId) {
        this.lineExecutionId = lineExecutionId;
    }

    @Override
    public LINE_EXECUTION_ID getExecutionId() {
        return lineExecutionId;
    }

    @Override
    public void setLineConfig(LINE_CONFIG lineConfig) {
        this.lineConfig = lineConfig;
    }

    @Override
    public LINE_CONFIG getLineConfig() {
        return lineConfig;
    }
}
