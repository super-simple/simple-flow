package org.ss.simpleflow.core.context;


import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.Map;

public interface SfProcessExecutionContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    void setParamArray(Map<String, Object>[] paramArray);

    Map<String, Object>[] getParamArray();

    void setResultArray(Map<String, Object>[] paramArray);

    Map<String, Object>[] getResultArray();

    void setProcessContext(SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext);

    SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> getProcessContext();

    void setNodeContextArray(SfNodeContext<NI, PCI, NEI, NC>[] nodeContextArray);

    SfNodeContext<NI, PCI, NEI, NC>[] getNodeContextArray();

    void setEdgeContextArray(SfEdgeContext<NI, EI, EEI, EC>[] edgeContextArray);

    SfEdgeContext<NI, EI, EEI, EC>[] getEdgeContextArray();
}
