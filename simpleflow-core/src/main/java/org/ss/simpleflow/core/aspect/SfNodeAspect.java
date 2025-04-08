package org.ss.simpleflow.core.aspect;

import org.ss.simpleflow.common.ListMap;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfNodeAspect<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, PEI> {

    void before(ListMap<String, Object> params,
                NC nc,
                SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                PC pc,
                SfProcessContext<NI, EI, PCI, NC,
                        EC, PC, PEI> processContext);

    void afterThrowing(Exception e,
                       EC ec,
                       SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                       PC pc,
                       SfProcessContext<NI, EI, PCI, NC,
                               EC, PC, PEI> processContext);

    void afterReturning(ListMap<String, Object> result,
                        NC nc,
                        SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                        PC pc,
                        SfProcessContext<NI, EI, PCI, NC,
                                EC, PC, PEI> processContext);

    void afterFinally(NC nc,
                      SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                      PC pc,
                      SfProcessContext<NI, EI, PCI, NC,
                              EC, PC, PEI> processContext);

}
