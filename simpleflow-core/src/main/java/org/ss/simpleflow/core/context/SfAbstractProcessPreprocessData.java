package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.index.SfIndexEntry;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.List;

public abstract class SfAbstractProcessPreprocessData<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {
    protected PC processConfig;
    protected int processConfigIndex;
    protected int startNodeConfigIndex;
    protected int nodeConfigListSize;
    protected int edgeConfigListSize;
    protected List<SfIndexEntry> nodeIndexEntryList;
    protected List<SfIndexEntry> edgeIndexEntryList;
    protected List<List<SfIndexEntry>> allOutgoingControlEdgeList;

    public PC getProcessConfig() {
        return processConfig;
    }

    public void setProcessConfig(PC processConfig) {
        this.processConfig = processConfig;
    }

    public int getProcessConfigIndex() {
        return processConfigIndex;
    }

    public void setProcessConfigIndex(int processConfigIndex) {
        this.processConfigIndex = processConfigIndex;
    }

    public int getStartNodeConfigIndex() {
        return startNodeConfigIndex;
    }

    public void setStartNodeConfigIndex(int startNodeConfigIndex) {
        this.startNodeConfigIndex = startNodeConfigIndex;
    }

    public int getNodeConfigListSize() {
        return nodeConfigListSize;
    }

    public void setNodeConfigListSize(int nodeConfigListSize) {
        this.nodeConfigListSize = nodeConfigListSize;
    }

    public int getEdgeConfigListSize() {
        return edgeConfigListSize;
    }

    public void setEdgeConfigListSize(int edgeConfigListSize) {
        this.edgeConfigListSize = edgeConfigListSize;
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
