package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

public abstract class SfAbstractNodeContext<NI, PCI, NEI,
        NC extends SfAbstractNodeConfig<NI, PCI>>
        extends SfAbstractVariableContext
        implements SfNodeContext<NI, PCI, NEI, NC> {

    protected NEI nodeExecutionId;

    @Override
    public void setNodeExecutionId(NEI nodeExecutionId) {
        this.nodeExecutionId = nodeExecutionId;
    }

    @Override
    public NEI getNodeExecutionId() {
        return nodeExecutionId;
    }

}
