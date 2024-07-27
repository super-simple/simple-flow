package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;

import java.io.Serializable;

public interface SfEdgeContext<NI, EI, EEI,
        EC extends SfAbstractEdgeConfig<EI, NI>>
        extends SfVariableContext, Serializable {

    void setEdgeExecutionId(EEI edgeExecutionId);

    EEI getEdgeExecutionId();

    void setEdgeConfig(EC edgeConfig);

    EC getEdgeConfig();

}
