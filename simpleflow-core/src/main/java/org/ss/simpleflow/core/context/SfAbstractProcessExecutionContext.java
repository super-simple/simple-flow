package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.Map;

public abstract class SfAbstractProcessExecutionContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> implements SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> {

    protected Map<String, Object>[] paramArray;

    protected Map<String, Object>[] resultArray;

    protected SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext;

    private SfNodeContext<NI, PCI, NEI, NC>[] nodeContextArray;

    private SfEdgeContext<NI, EI, EEI, EC>[] edgeContextArray;

    @Override
    public Map<String, Object>[] getParamArray() {
        return paramArray;
    }

    @Override
    public void setParamArray(Map<String, Object>[] paramArray) {
        this.paramArray = paramArray;
    }

    @Override
    public Map<String, Object>[] getResultArray() {
        return resultArray;
    }

    @Override
    public void setResultArray(Map<String, Object>[] resultArray) {
        this.resultArray = resultArray;
    }

    @Override
    public SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> getProcessContext() {
        return processContext;
    }

    @Override
    public void setProcessContext(SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext) {
        this.processContext = processContext;
    }

    @Override
    public SfNodeContext<NI, PCI, NEI, NC>[] getNodeContextArray() {
        return nodeContextArray;
    }

    @Override
    public void setNodeContextArray(SfNodeContext<NI, PCI, NEI, NC>[] nodeContextArray) {
        this.nodeContextArray = nodeContextArray;
    }

    @Override
    public SfEdgeContext<NI, EI, EEI, EC>[] getEdgeContextArray() {
        return edgeContextArray;
    }

    @Override
    public void setEdgeContextArray(SfEdgeContext<NI, EI, EEI, EC>[] edgeContextArray) {
        this.edgeContextArray = edgeContextArray;
    }

}
