package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public abstract class SfAbstractProcessContext<NI, EI, PCI, NC extends SfAbstractNodeConfig<NI, PCI>, EC extends SfAbstractEdgeConfig<EI, NI>, PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>, PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>, PEI> implements SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> {

    protected SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext;

    protected PEI processExecutionId;

    protected PC processConfig;

    protected PCI processConfigId;

    @Override
    public void setParentProcessContext(SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext) {
        this.processContext = processContext;
    }

    @Override
    public SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> getParentProcessContext() {
        return processContext;
    }

    @Override
    public void setProcessExecutionId(PEI processExecutionId) {
        this.processExecutionId = processExecutionId;
    }

    @Override
    public PEI getProcessExecutionId() {
        return processExecutionId;
    }

    @Override
    public void setProcessConfig(PC processConfig) {
        this.processConfig = processConfig;
    }

    @Override
    public PC getProcessConfig() {
        return processConfig;
    }

    @Override
    public void setProcessConfigId(PCI processConfigId) {
        this.processConfigId = processConfigId;
    }

    @Override
    public PCI getProcessConfigId() {
        return processConfigId;
    }

}
