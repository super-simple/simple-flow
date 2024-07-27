package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfComponentExecutionIdGenerator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    NEI generateNodeExecutionId(
            SfNodeContext<NI, PCI, NEI, NC> nodeContext,
            SfProcessContext<NI, EI, PCI, NC,
                    EC, PCG, PC, PEI> processContext);

    EEI generateEdgeExecutionId(
            SfEdgeContext<NI, EI, EEI, EC> edgeContext,
            SfProcessContext<NI, EI, PCI, NC,
                    EC, PCG, PC, PEI> processContext);
}
