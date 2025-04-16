package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public abstract class SfAbstractWholeExecutionContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> implements SfWholeExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> {

    private SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainProcessExecuteContext;

    private SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>[] subProcessExecutionContextArray;

    @Override
    public SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> getMainProcessExecuteContext() {
        return mainProcessExecuteContext;
    }

    @Override
    public void setMainProcessExecuteContext(SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainProcessExecuteContext) {
        this.mainProcessExecuteContext = mainProcessExecuteContext;
    }

    @Override
    public SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>[] getSubProcessExecutionContextArray() {
        return subProcessExecutionContextArray;
    }

    @Override
    public void setSubProcessExecutionContextArray(SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>[] subProcessExecutionContextArray) {
        this.subProcessExecutionContextArray = subProcessExecutionContextArray;
    }
}
