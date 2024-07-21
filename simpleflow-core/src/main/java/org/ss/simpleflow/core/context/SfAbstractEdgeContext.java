package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;

public abstract class SfAbstractEdgeContext<NODE_ID, EDGE_ID, EDGE_EXECUTION_ID, EDGE_CONFIG
        extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>>
        extends SfDefaultVariableContext
        implements SfEdgeContext<NODE_ID, EDGE_ID, EDGE_EXECUTION_ID, EDGE_CONFIG> {

    protected EDGE_EXECUTION_ID edgeExecutionId;

    protected EDGE_CONFIG edgeConfig;

    @Override
    public void setEdgeExecutionId(EDGE_EXECUTION_ID edgeExecutionId) {
        this.edgeExecutionId = edgeExecutionId;
    }

    @Override
    public EDGE_EXECUTION_ID getEdgeExecutionId() {
        return edgeExecutionId;
    }

    @Override
    public void setEdgeConfig(EDGE_CONFIG edgeConfig) {
        this.edgeConfig = edgeConfig;
    }

    @Override
    public EDGE_CONFIG getEdgeConfig() {
        return edgeConfig;
    }
}
