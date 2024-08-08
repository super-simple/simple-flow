package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.*;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfContextFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    EC createEdgeContext();

    NC createNodeContext();

    SfProcessContext<NI, EI, PCI, NC, EC,
            PCG, PC,
            PEI> createProcessContext();

    SfExecutionGlobalContext<NI, EI, PCI, NC, EC,
            PCG, PC,
            NEI, EEI, PEI> createExecutionGlobalContext();

    SfExecutionProcessContext<NI, EI, PCI, NC, EC,
            PCG, PC,
            NEI, EEI, PEI> createExecutionProcessContext();

    SfExecutionProcessExternalContext<NI, EI, PCI, NC, EC,
            PCG, PC,
            NEI, EEI, PEI> createExecutionProcessExternalContext();

    SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC,
            PCG, PC,
            NEI, EEI, PEI> createExecutionProcessInternalContext();

    SfValidationGlobalContext<NI, EI, PCI, NC, EC,
            PCG, PC,
            NEI, EEI, PEI> createValidationGlobalContext();

    SfValidationProcessContext<NI, EI, PCI, NC, EC,
            PCG, PC,
            NEI, EEI, PEI> createProcessValidationContext();

    SfProcessReturn<PEI> createProcessReturn();
}
