package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.List;

public abstract class SfWholePreprocessData<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    protected SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainExecutionProcessContext;

    protected List<SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>> subExecutionProcessContextList;

    public SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> getMainExecutionProcessContext() {
        return mainExecutionProcessContext;
    }

    public void setMainExecutionProcessContext(SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainExecutionProcessContext) {
        this.mainExecutionProcessContext = mainExecutionProcessContext;
    }

    public List<SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>> getSubExecutionProcessContextList() {
        return subExecutionProcessContextList;
    }

    public void setSubExecutionProcessContextList(List<SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>> subExecutionProcessContextList) {
        this.subExecutionProcessContextList = subExecutionProcessContextList;
    }
}
