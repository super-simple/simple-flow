package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.iterator.SfAroundIterator;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfAroundIteratorFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, PEI> {

    SfAroundIterator<NI, EI, PCI, NC,
            EC, PCG, PC, NEI, PEI>
    createAroundIterator(
            SfNodeContext<NI, PCI, NEI, NC> nodeContext,
            SfProcessContext<NI, EI, PCI, NC,
                    EC, PCG, PC, PEI> processContext);
}
