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

    SfNodeContext<NI, PCI, NEI, NC> createNodeContext();

    SfEdgeContext<NI, EI, EEI, EC> createEdgeContext();

    SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> createProcessContext();

    SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> createProcessExecutionContext();

    SfWholeExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> createWholeExecutionContext();
}
