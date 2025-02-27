package org.ss.simpleflow.core.edge;

import org.ss.simpleflow.common.ListMap;
import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.context.SfVariableContext;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfControlEdge<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        EEI, PEI> extends SfComponent {

    boolean executeControlEdge(ListMap<String, Object> params,
                               SfEdgeContext<NI, EI, EEI, EC> edgeContext,
                               SfProcessContext<NI, EI, PCI, NC,
                                       EC, PC, PEI> processContext,
                               SfVariableContext processVariableContext) throws Exception;

}
