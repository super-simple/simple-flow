package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.io.Serializable;

public interface SfNodeContext<NI, PCI, NEI,
        NC extends SfAbstractNodeConfig<NI, PCI>>
        extends SfVariableContext, Serializable {

    void setNodeExecutionId(NEI nodeExecutionId);

    NEI getNodeExecutionId();

    void setNodeConfig(NC nodeConfig);

    NC getNodeConfig();

}
