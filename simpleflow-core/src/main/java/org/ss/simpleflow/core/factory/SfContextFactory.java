package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.context.SfProcessExecuteResult;
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

    SfProcessExecuteResult<PEI> createProcessExecuteResult();
}
