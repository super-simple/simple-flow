package org.ss.simpleflow.core.node;

import org.ss.simpleflow.common.ListMap;
import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.context.SfProcessVariableContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.Map;

public interface SfNode<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, PEI> extends SfComponent {

    Map<String, Object> executeNode(ListMap<String, Object> params,
                                    SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                                    SfProcessContext<NI, EI, PCI, NC,
                                            EC, PC, PEI> processContext,
                                    SfProcessVariableContext processVariableContext) throws Exception;
}
