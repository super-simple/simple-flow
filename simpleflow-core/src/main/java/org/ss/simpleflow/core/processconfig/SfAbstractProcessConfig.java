package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.util.List;

public abstract class SfAbstractProcessConfig<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>> implements SfProcessConfig<NI, EI, PCI, NC, EC, PCG> {

    protected PCI id;
    protected List<NC> nodeConfigList;
    protected List<EC> edgeConfigList;
    protected List<PCG> subProcessConfigList;


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

    public void setSubProcessConfigList(List<PCG> subProcessConfigList) {
        this.subProcessConfigList = subProcessConfigList;
    }

    @Override
    public List<PCG> getSubProcessConfigList() {
        return subProcessConfigList;
    }

}
