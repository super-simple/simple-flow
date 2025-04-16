package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfComponentExecutionIdGenerator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    NEI generateNodeExecutionId(
            EC ec,
            SfNodeContext<NI, PCI, NEI, NC> nodeContext,
            PC pc,
            SfProcessContext<NI, EI, PCI, NC,
                    EC, PC, PEI> processContext);

    EEI generateEdgeExecutionId(
            EC ec,
            SfEdgeContext<NI, EI, EEI, EC> edgeContext,
            PC pc,
            SfProcessContext<NI, EI, PCI, NC,
                    EC, PC, PEI> processContext);
}
