package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.util.List;

public abstract class SfAbstractProcessConfig<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>> implements SfProcessConfig<NI, EI, PCI, NC, EC> {

    protected PCI id;
    protected List<NC> nodeConfigList;
    protected List<EC> edgeConfigList;

    public void setId(PCI id) {
        this.id = id;
    }

    @Override
    public PCI getId() {
        return id;
    }

    public void setNodeConfigList(List<NC> nodeConfigList) {
        this.nodeConfigList = nodeConfigList;
    }

    @Override
    public List<NC> getNodeConfigList() {
        return nodeConfigList;
    }

    public void setEdgeConfigList(List<EC> edgeConfigList) {
        this.edgeConfigList = edgeConfigList;
    }

    @Override
    public List<EC> getEdgeConfigList() {
        return edgeConfigList;
    }

}
