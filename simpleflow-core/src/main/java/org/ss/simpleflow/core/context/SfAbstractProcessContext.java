package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public abstract class SfAbstractProcessContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>, EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>, PEI>
        extends SfAbstractVariableContext
        implements SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> {

    protected boolean root = false;
    protected SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> rootProcessContext;
    protected SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> parentProcessContext;

    protected PEI processExecutionId;

    protected PC processConfig;

    @Override
    public boolean isRoot() {
        return root;
    }

    @Override
    public void setRoot(boolean root) {
        this.root = root;
    }

    @Override
    public void setRootProcessContext(SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> rootProcessContext) {
        this.rootProcessContext = rootProcessContext;
    }

    @Override
    public SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> getRootProcessContext() {
        return rootProcessContext;
    }

    @Override
    public void setParentProcessContext(SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext) {
        this.parentProcessContext = processContext;
    }

    @Override
    public SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> getParentProcessContext() {
        return parentProcessContext;
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

}
