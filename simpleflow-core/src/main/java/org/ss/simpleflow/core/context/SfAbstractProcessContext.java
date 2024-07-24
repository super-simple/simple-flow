package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public abstract class SfAbstractProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfAbstractProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        PROCESS_EXECUTION_ID>
        extends SfDefaultVariableContext
        implements SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG,
        EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> {

    protected SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, EDGE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext;
    protected PROCESS_EXECUTION_ID processExecutionId;

    protected PROCESS_CONFIG processConfig;

    protected PROCESS_CONFIG_ID processConfigId;

    @Override
    public void setParentProcessContext(SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, EDGE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext) {
        this.processContext = processContext;
    }

    @Override
    public SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, EDGE_CONFIG,
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

    @Override
    public void setProcessConfigId(PROCESS_CONFIG_ID processConfigId) {
        this.processConfigId = processConfigId;
    }

    @Override
    public PROCESS_CONFIG_ID getProcessConfigId() {
        return processConfigId;
    }
}
