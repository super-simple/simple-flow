package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;

public interface SfEdgeContext<NI, EI, EEI,
        EC extends SfAbstractEdgeConfig<EI, NI>>
        extends SfVariableContext {

    void setEdgeExecutionId(EEI edgeExecutionId);

    EEI getEdgeExecutionId();

    void setEdgeConfig(EC edgeConfig);

    EC getEdgeConfig();

}
