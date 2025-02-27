package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.edge.SfDataEdge;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfDataEdgeFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        EEI, PEI> {

    SfDataEdge<NI, EI, PCI, NC,
            EC, PC, EEI, PEI>
    createDataEdge(
            SfEdgeContext<NI, EI, EEI, EC> edgeContext,
            SfProcessContext<NI, EI, PCI, NC,
                    EC, PC, PEI> processContext);

}
