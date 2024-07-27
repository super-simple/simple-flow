package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

public abstract class SfAbstractNodeContext<NI, PCI, NEI,
        NC extends SfAbstractNodeConfig<NI, PCI>>
        extends SfDefaultVariableContext
        implements SfNodeContext<NI, PCI, NEI, NC> {

    protected NEI nodeExecutionId;

    protected NC nodeConfig;

    @Override
    public void setNodeExecutionId(NEI nodeExecutionId) {
        this.nodeExecutionId = nodeExecutionId;
    }

    @Override
    public NEI getNodeExecutionId() {
        return nodeExecutionId;
    }

    @Override
    public void setNodeConfig(NC nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    @Override
    public NC getNodeConfig() {
        return nodeConfig;
    }

}
