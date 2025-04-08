package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.event.SfEvent;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfEventFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, PEI> {

    SfEvent<NI, EI, PCI, NC,
            EC, PC, NEI, PEI> createEvent(NC nc,
                                          SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                                          PC pc,
                                          SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext);

}
