package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.index.SfIndexEntry;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SfValidationProcessContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {
    protected NC startNodeConfig;
    protected int startNodeConfigIndex;
    protected int controlEdgeCount;
    protected int dataEdgeCount;

    protected Map<NI, NC> nodeConfigMap;

    protected Set<PCI> subProcessConfigIdSet;

    protected List<NC> nodeConfigList;
    protected List<EC> edgeConfigList;

    protected List<SfIndexEntry> nodeIndexEntryList;

    protected List<SfIndexEntry> edgeIndexEntryList;

    protected List<SfIndexEntry> controlEdgeIndexEntryList;

    protected List<List<SfIndexEntry>> allOutgoingControlEdgeList;

    public NC getStartNodeConfig() {
        return startNodeConfig;
    }

    public void setStartNodeConfig(NC startNodeConfig) {
        this.startNodeConfig = startNodeConfig;
    }

    public int getStartNodeConfigIndex() {
        return startNodeConfigIndex;
    }

    public void setStartNodeConfigIndex(int startNodeConfigIndex) {
        this.startNodeConfigIndex = startNodeConfigIndex;
    }

    public int getControlEdgeCount() {
        return controlEdgeCount;
    }

    public void setControlEdgeCount(int controlEdgeCount) {
        this.controlEdgeCount = controlEdgeCount;
    }

    public int getDataEdgeCount() {
        return dataEdgeCount;
    }

    public void setDataEdgeCount(int dataEdgeCount) {
        this.dataEdgeCount = dataEdgeCount;
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

    public List<NC> getNodeConfigList() {
        return nodeConfigList;
    }

    public void setNodeConfigList(List<NC> nodeConfigList) {
        this.nodeConfigList = nodeConfigList;
    }

    public List<EC> getEdgeConfigList() {
        return edgeConfigList;
    }

    public void setEdgeConfigList(List<EC> edgeConfigList) {
        this.edgeConfigList = edgeConfigList;
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

    public List<SfIndexEntry> getControlEdgeIndexEntryList() {
        return controlEdgeIndexEntryList;
    }

    public void setControlEdgeIndexEntryList(List<SfIndexEntry> controlEdgeIndexEntryList) {
        this.controlEdgeIndexEntryList = controlEdgeIndexEntryList;
    }

    public List<List<SfIndexEntry>> getAllOutgoingControlEdgeList() {
        return allOutgoingControlEdgeList;
    }

    public void setAllOutgoingControlEdgeList(List<List<SfIndexEntry>> allOutgoingControlEdgeList) {
        this.allOutgoingControlEdgeList = allOutgoingControlEdgeList;
    }

}
