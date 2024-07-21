package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;

import java.io.Serializable;

public interface SfLineContext<NODE_ID, EDGE_ID, EDGE_EXECUTION_ID,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>>
        extends SfVariableContext, Serializable {

    void setLineExecutionId(EDGE_EXECUTION_ID lineExecutionId);

    EDGE_EXECUTION_ID getLineExecutionId();

    void setLineConfig(EDGE_CONFIG edgeConfig);

    EDGE_CONFIG getLineConfig();

}
