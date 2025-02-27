package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.List;

public abstract class SfExecutionWholeContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    protected SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainExecutionProcessContext;

    protected List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> subExecutionProcessContextList;

    public SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> getMainExecutionProcessContext() {
        return mainExecutionProcessContext;
    }

    public void setMainExecutionProcessContext(SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainExecutionProcessContext) {
        this.mainExecutionProcessContext = mainExecutionProcessContext;
    }

    public List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> getSubExecutionProcessContextList() {
        return subExecutionProcessContextList;
    }

    public void setSubExecutionProcessContextList(List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> subExecutionProcessContextList) {
        this.subExecutionProcessContextList = subExecutionProcessContextList;
    }
}
