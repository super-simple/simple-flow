package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.io.Serializable;

public interface SfProcessContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        PEI> extends Serializable {

    void setParentProcessContext(SfProcessContext<NI, EI, PCI,
            NC, EC,
            PC, PEI> processContext);

    SfProcessContext<NI, EI, PCI,
            NC, EC,
            PC, PEI> getParentProcessContext();

    void setProcessExecutionId(PEI processExecutionId);

    PEI getProcessExecutionId();

    void setProcessConfig(PC processConfig);

    PC getProcessConfig();

    void setProcessConfigId(PCI processConfigId);

    PCI getProcessConfigId();
}
