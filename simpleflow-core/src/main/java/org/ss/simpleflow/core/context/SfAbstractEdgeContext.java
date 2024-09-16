package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;

public abstract class SfAbstractEdgeContext<NI, EI, EEI, EC
        extends SfAbstractEdgeConfig<EI, NI>>
        extends SfAbstractVariableContext
        implements SfEdgeContext<NI, EI, EEI, EC> {

    protected EEI edgeExecutionId;

    protected EC edgeConfig;

    @Override
    public void setEdgeExecutionId(EEI edgeExecutionId) {
        this.edgeExecutionId = edgeExecutionId;
    }

    @Override
    public EEI getEdgeExecutionId() {
        return edgeExecutionId;
    }

    @Override
    public void setEdgeConfig(EC edgeConfig) {
        this.edgeConfig = edgeConfig;
    }

    @Override
    public EC getEdgeConfig() {
        return edgeConfig;
    }
}
