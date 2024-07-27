package org.ss.simpleflow.core.edge;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.Map;

public interface SfControEdge<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        EEI, PEI> extends SfComponent {

    boolean executeControlEdge(Map<String, Object> params,
                               SfEdgeContext<NI, EI, EEI, EC> edgeContext,
                               SfProcessContext<NI, EI, PCI, NC,
                                       EC, PCG, PC, PEI> processContext) throws Exception;

}
