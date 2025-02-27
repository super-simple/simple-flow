package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.edge.SfControlEdge;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfControlEdgeFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        EEI, PEI> {

    SfControlEdge<NI, EI, PCI, NC, EC, PC, EEI, PEI>
    createControlEdge(
            SfEdgeContext<NI, EI, EEI, EC> edgeContext,
            SfProcessContext<NI, EI, PCI, NC,
                    EC, PC, PEI> processContext);

}
