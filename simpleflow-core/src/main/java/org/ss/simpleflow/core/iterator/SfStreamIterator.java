package org.ss.simpleflow.core.iterator;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.context.SfProcessVariableContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.List;
import java.util.Map;

public interface SfStreamIterator<NI, EI, PCI, NC extends SfAbstractNodeConfig<NI, PCI>, EC extends SfAbstractEdgeConfig<EI, NI>, PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>, PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>, NEI, PEI> extends SfComponent {

    List<Map<String, Object>> map(Object[] params,
                                  SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                                  SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext) throws Exception;

    default void collect(Object[] params,
                         Map<String, Object> resultMap,
                         SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                         SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
                         SfProcessVariableContext processVariableContext) throws Exception {
    }

}
