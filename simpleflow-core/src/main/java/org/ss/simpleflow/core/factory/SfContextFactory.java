package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.*;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfContextFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    EC createEdgeContext();

    NC createNodeContext();

    SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> createProcessContext();

    SfExecutionWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> createExecutionGlobalContext();

    SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> createExecutionProcessContext();

    SfExecutionProcessExternalContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> createExecutionProcessExternalContext();

    SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> createExecutionProcessInternalContext();

    SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> createValidationWholeContext();

    SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> createProcessValidationContext();

    SfProcessExecuteResult<PEI> createProcessExecuteResult();
}
