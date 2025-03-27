package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.List;

public interface SfWholeExecutionContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    void setProcessExecutionId(PEI processExecutionId);

    PEI getProcessExecutionId();

    void setMainProcessExecuteContext(SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> processExecutionContext);

    SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> getMainProcessExecuteContext();

    void setSubProcessExecutionContextList(List<SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> processExecutionContextList);

    List<SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>> getSubProcessExecutionContextList();
}
