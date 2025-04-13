package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

public abstract class SfAbstractProcessConfig<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>> implements SfProcessConfig<NI, EI, PCI, NC, EC> {

    protected PCI id;
    protected NC[] nodeConfigArray;
    protected EC[] edgeConfigArray;

    public void setId(PCI id) {
        this.id = id;
    }

    @Override
    public PCI getId() {
        return id;
    }

    public void setNodeConfigArray(NC[] nodeConfigArray) {
        this.nodeConfigArray = nodeConfigArray;
    }

    @Override
    public NC[] getNodeConfigArray() {
        return nodeConfigArray;
    }

    public void setEdgeConfigArray(EC[] edgeConfigArray) {
        this.edgeConfigArray = edgeConfigArray;
    }

    @Override
    public EC[] getEdgeConfigArray() {
        return edgeConfigArray;
    }

}
