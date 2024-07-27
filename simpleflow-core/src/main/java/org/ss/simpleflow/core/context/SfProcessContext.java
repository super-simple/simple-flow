package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.io.Serializable;

public interface SfProcessContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        PEI> extends SfVariableContext, Serializable {

    void setParentProcessContext(SfProcessContext<NI, EI, PCI,
            NC, EC,
            PCG, PC, PEI> processContext);

    SfProcessContext<NI, EI, PCI,
            NC, EC,
            PCG, PC, PEI> getParentProcessContext();

    void setProcessExecutionId(PEI processExecutionId);

    PEI getProcessExecutionId();

    void setProcessConfig(PC processConfig);

    PC getProcessConfig();

    void setProcessConfigId(PCI processConfigId);

    PCI getProcessConfigId();
}
