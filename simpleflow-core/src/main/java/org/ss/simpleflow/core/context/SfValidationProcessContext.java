package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.Map;
import java.util.Set;

public abstract class SfValidationProcessContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    protected NC startNodeConfig;

    protected Map<NI, NC> nodeConfigMap;

    protected Set<PCI> subProcessConfigIdSet;

    public NC getStartNodeConfig() {
        return startNodeConfig;
    }

    public void setStartNodeConfig(NC startNodeConfig) {
        this.startNodeConfig = startNodeConfig;
    }

    public Map<NI, NC> getNodeConfigMap() {
        return nodeConfigMap;
    }

    public void setNodeConfigMap(Map<NI, NC> nodeConfigMap) {
        this.nodeConfigMap = nodeConfigMap;
    }

    public Set<PCI> getSubProcessConfigIdSet() {
        return subProcessConfigIdSet;
    }

    public void setSubProcessConfigIdSet(Set<PCI> subProcessConfigIdSet) {
        this.subProcessConfigIdSet = subProcessConfigIdSet;
    }
}
