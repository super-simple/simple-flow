package org.ss.simpleflow.core.iterator;


import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.List;
import java.util.Map;

public interface SfStreamIterator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>, NEI, PEI> extends SfComponent {

    List<Map<String, Object>> map(Map<String, Object> params,
                                  NC nc,
                                  SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                                  PC pc,
                                  SfProcessContext<NI, EI, PCI, NC,
                                          EC, PC, PEI> processContext) throws Exception;

    default void collect(Map<String, Object> params,
                         Map<String, Object> resultMap,
                         NC nc,
                         SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                         PC pc,
                         SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext) throws Exception {
    }

}
