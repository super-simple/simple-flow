package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.index.SfIndexEntry;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.List;

public abstract class SfExecutionProcessInternalContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    protected SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext;

    protected int processConfigIndex;

    protected int startNodeConfigIndex;

    protected List<SfIndexEntry> nodeIndexEntryList;
    protected List<NC> nodeConfigList;
    protected List<SfIndexEntry> edgeIndexEntryList;
    protected List<EC> edgeConfigList;

    protected SfNodeContext<NI, PCI, NEI, NC> nodeContextList;

    protected List<List<SfIndexEntry>> allOutgoingControlEdgeList;

    public SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> getProcessContext() {
        return processContext;
    }

    public void setProcessContext(SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext) {
        this.processContext = processContext;
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

    public List<SfIndexEntry> getNodeIndexEntryList() {
        return nodeIndexEntryList;
    }

    public void setNodeIndexEntryList(List<SfIndexEntry> nodeIndexEntryList) {
        this.nodeIndexEntryList = nodeIndexEntryList;
    }

    public void setNodeConfigList(List<NC> nodeConfigList) {
        this.nodeConfigList = nodeConfigList;
    }

    public List<NC> getNodeConfigList() {
        return nodeConfigList;
    }

    public List<SfIndexEntry> getEdgeIndexEntryList() {
        return edgeIndexEntryList;
    }

    public void setEdgeIndexEntryList(List<SfIndexEntry> edgeIndexEntryList) {
        this.edgeIndexEntryList = edgeIndexEntryList;
    }

    public void setEdgeConfigList(List<EC> edgeConfigList) {
        this.edgeConfigList = edgeConfigList;
    }

    public List<EC> getEdgeConfigList() {
        return edgeConfigList;
    }

    public List<List<SfIndexEntry>> getAllOutgoingControlEdgeList() {
        return allOutgoingControlEdgeList;
    }

    public void setAllOutgoingControlEdgeList(List<List<SfIndexEntry>> allOutgoingControlEdgeList) {
        this.allOutgoingControlEdgeList = allOutgoingControlEdgeList;
    }
}
