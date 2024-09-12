package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.index.SfIndexEntry;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SfValidationProcessContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    protected NC startNodeConfig;

    protected Integer startNodeConfigIndex;

    protected Map<NI, NC> nodeConfigMap;

    protected Set<PCI> subProcessConfigIdSet;

    protected List<SfIndexEntry> nodeIndexEntryList;

    protected List<SfIndexEntry> edgeIndexEntryList;

    protected List<List<SfIndexEntry>> allOutgoingControlEdgeList;

    public NC getStartNodeConfig() {
        return startNodeConfig;
    }

    public void setStartNodeConfig(NC startNodeConfig) {
        this.startNodeConfig = startNodeConfig;
    }

    public Integer getStartNodeConfigIndex() {
        return startNodeConfigIndex;
    }

    public void setStartNodeConfigIndex(Integer startNodeConfigIndex) {
        this.startNodeConfigIndex = startNodeConfigIndex;
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

    public List<SfIndexEntry> getNodeIndexEntryList() {
        return nodeIndexEntryList;
    }

    public void setNodeIndexEntryList(List<SfIndexEntry> nodeIndexEntryList) {
        this.nodeIndexEntryList = nodeIndexEntryList;
    }

    public List<SfIndexEntry> getEdgeIndexEntryList() {
        return edgeIndexEntryList;
    }

    public void setEdgeIndexEntryList(List<SfIndexEntry> edgeIndexEntryList) {
        this.edgeIndexEntryList = edgeIndexEntryList;
    }

    public List<List<SfIndexEntry>> getAllOutgoingControlEdgeList() {
        return allOutgoingControlEdgeList;
    }

    public void setAllOutgoingControlEdgeList(List<List<SfIndexEntry>> allOutgoingControlEdgeList) {
        this.allOutgoingControlEdgeList = allOutgoingControlEdgeList;
    }

}
