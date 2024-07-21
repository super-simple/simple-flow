package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;

import java.io.Serializable;

public interface SfEdgeContext<NODE_ID, EDGE_ID, EDGE_EXECUTION_ID,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>>
        extends SfVariableContext, Serializable {

    void setEdgeExecutionId(EDGE_EXECUTION_ID edgeExecutionId);

    EDGE_EXECUTION_ID getEdgeExecutionId();

    void setEdgeConfig(EDGE_CONFIG edgeConfig);

    EDGE_CONFIG getEdgeConfig();

}
