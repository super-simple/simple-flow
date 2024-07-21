package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;

public abstract class SfAbstractLineContext<NODE_ID, EDGE_ID, EDGE_EXECUTION_ID, EDGE_CONFIG
        extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>>
        extends SfDefaultVariableContext
        implements SfLineContext<NODE_ID, EDGE_ID, EDGE_EXECUTION_ID, EDGE_CONFIG> {

    protected EDGE_EXECUTION_ID lineExecutionId;

    protected EDGE_CONFIG edgeConfig;

    @Override
    public void setLineExecutionId(EDGE_EXECUTION_ID lineExecutionId) {
        this.lineExecutionId = lineExecutionId;
    }

    @Override
    public EDGE_EXECUTION_ID getLineExecutionId() {
        return lineExecutionId;
    }

    @Override
    public void setLineConfig(EDGE_CONFIG edgeConfig) {
        this.edgeConfig = edgeConfig;
    }

    @Override
    public EDGE_CONFIG getLineConfig() {
        return edgeConfig;
    }
}
