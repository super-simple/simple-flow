package org.ss.simpleflow.core.aspect;

import org.ss.simpleflow.common.ListMap;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfProcessAspect<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        PEI> {

    void before(ListMap<String, Object> params,
                PC pc,
                SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext);

    void afterThrowing(Exception e,
                       PC pc,
                       SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext);

    void afterReturning(ListMap<String, Object> result,
                        PC pc,
                        SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext);

    void afterFinally(PC pc,
                      SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext);

}
