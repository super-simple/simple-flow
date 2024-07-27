package org.ss.simpleflow.core.aspect;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.Map;

public interface SfNodeAspect<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, PEI> {

    void before(Map<String, Object> params,
                SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                SfProcessContext<NI, EI, PCI, NC,
                        EC, PCG, PC, PEI> processContext);

    void afterThrowing(Exception e,
                       SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                       SfProcessContext<NI, EI, PCI, NC,
                               EC, PCG, PC, PEI> processContext);

    void afterReturning(Map<String, Object> result,
                        SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                        SfProcessContext<NI, EI, PCI, NC,
                                EC, PCG, PC, PEI> processContext);

    void afterFinally(SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                      SfProcessContext<NI, EI, PCI, NC,
                              EC, PCG, PC, PEI> processContext);

    void afterCancel(SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                     SfProcessContext<NI, EI, PCI, NC,
                             EC, PCG, PC, PEI> processContext);

}
