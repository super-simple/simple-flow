package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfWholeExecutionContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    void setMainProcessExecuteContext(SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainProcessExecuteContext);

    SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> getMainProcessExecuteContext();

    void setSubProcessExecutionContextArray(SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>[] subProcessExecutionContextArray);

    SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>[] getSubProcessExecutionContextArray();
}
