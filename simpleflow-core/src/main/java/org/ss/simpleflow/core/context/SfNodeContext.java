package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

public interface SfNodeContext<NI, PCI, NEI,
        NC extends SfAbstractNodeConfig<NI, PCI>>
        extends SfVariableContext {

    void setNodeExecutionId(NEI nodeExecutionId);

    NEI getNodeExecutionId();

    void setNodeConfig(NC nodeConfig);

    NC getNodeConfig();

}
