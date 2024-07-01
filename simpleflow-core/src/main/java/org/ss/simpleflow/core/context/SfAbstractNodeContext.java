package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

public abstract class SfAbstractNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>>
        extends SfDefaultVariableContext
        implements SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID, NODE_CONFIG> {

    protected NODE_EXECUTION_ID nodeExecutionId;

    protected NODE_CONFIG nodeConfig;

    @Override
    public void setNodeExecutionId(NODE_EXECUTION_ID nodeExecutionId) {
        this.nodeExecutionId = nodeExecutionId;
    }

    @Override
    public NODE_EXECUTION_ID getNodeExecutionId() {
        return nodeExecutionId;
    }

    @Override
    public void setNodeConfig(NODE_CONFIG nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    @Override
    public NODE_CONFIG getNodeConfig() {
        return nodeConfig;
    }

}
