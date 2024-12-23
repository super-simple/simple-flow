package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.List;

public abstract class SfExecutionProcessExternalContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    protected SfVariableContext processVariableContext;
    protected List<SfNodeFunctionContext> nodeFunctionContextList;

    public SfVariableContext getProcessVariableContext() {
        return processVariableContext;
    }

    public void setProcessVariableContext(SfVariableContext processVariableContext) {
        this.processVariableContext = processVariableContext;
    }

    public List<SfNodeFunctionContext> getNodeFunctionContextList() {
        return nodeFunctionContextList;
    }

    public void setNodeFunctionContextList(List<SfNodeFunctionContext> nodeFunctionContextList) {
        this.nodeFunctionContextList = nodeFunctionContextList;
    }
}
