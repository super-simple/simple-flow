package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public abstract class SfExecutionProcessContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {
    protected SfExecutionProcessExternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionExternalContext;
    protected SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionInternalContext;

    public SfExecutionProcessExternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> getExecutionExternalContext() {
        return executionExternalContext;
    }

    public void setExecutionExternalContext(SfExecutionProcessExternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionExternalContext) {
        this.executionExternalContext = executionExternalContext;
    }

    public SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> getExecutionInternalContext() {
        return executionInternalContext;
    }

    public void setExecutionInternalContext(SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionInternalContext) {
        this.executionInternalContext = executionInternalContext;
    }
}
