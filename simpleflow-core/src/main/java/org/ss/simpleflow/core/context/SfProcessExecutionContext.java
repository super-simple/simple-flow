package org.ss.simpleflow.core.context;

import org.ss.simpleflow.common.ListMap;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.List;

public interface SfProcessExecutionContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    void setParamList(List<ListMap<String, Object>> paramList);

    List<ListMap<String, Object>> getParamList();

    void setResultList(List<ListMap<String, Object>> paramList);

    List<ListMap<String, Object>> getResultList();

    void setProcessContext(SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext);

    SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> getProcessContext();

    void setNodeContextList(List<SfNodeContext<NI, PCI, NEI, NC>> nodeContextList);

    List<SfNodeContext<NI, PCI, NEI, NC>> getNodeContextList();

    void setEdgeContextList(List<SfEdgeContext<NI, EI, EEI, EC>> edgeContextList);

    List<SfEdgeContext<NI, EI, EEI, EC>> getEdgeContextList();
}
