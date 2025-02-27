package org.ss.simpleflow.core.aspect;

import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.Map;

public interface SfComponentInterceptor<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    boolean cancel(Map<String, Object> params,
                   SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                   SfProcessContext<NI, EI, PCI, NC,
                           EC, PC, PEI> processContext);

    boolean cancel(Map<String, Object> params,
                   SfEdgeContext<NI, EI, EEI, EC> edgeContext,
                   SfProcessContext<NI, EI, PCI,
                           NC, EC,
                           PC, PEI> processContext);

}
