package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.List;
import java.util.Set;

public abstract class SfExecutionGlobalContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    protected Set<PCI> referencedSubProcessConfigIdSet;
    protected SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionProcessContext;
    protected List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList;

    public Set<PCI> getReferencedSubProcessConfigIdSet() {
        return referencedSubProcessConfigIdSet;
    }

    public void setReferencedSubProcessConfigIdSet(Set<PCI> referencedSubProcessConfigIdSet) {
        this.referencedSubProcessConfigIdSet = referencedSubProcessConfigIdSet;
    }

    public SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> getMainExecutionProcessContext() {
        return mainExecutionProcessContext;
    }

    public void setMainExecutionProcessContext(SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionProcessContext) {
        this.mainExecutionProcessContext = mainExecutionProcessContext;
    }

    public List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> getSubExecutionProcessContextList() {
        return subExecutionProcessContextList;
    }

    public void setSubExecutionProcessContextList(List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList) {
        this.subExecutionProcessContextList = subExecutionProcessContextList;
    }
}
