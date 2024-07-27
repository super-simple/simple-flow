package org.ss.simpleflow.core.aspect;

import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.Map;

public interface SfEdgeAspect<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        EEI, PEI> {

    void before(Map<String, Object> params,
                SfEdgeContext<NI, EI, EEI, EC> edgeContext,
                SfProcessContext<NI, EI, PCI,
                        NC, EC,
                        PCG, PC, PEI> processContext);

    void afterThrowing(Exception e,
                       SfEdgeContext<NI, EI, EEI, EC> edgeContext,
                       SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext);

    void afterReturning(Map<String, Object> result,
                        SfEdgeContext<NI, EI, EEI, EC> edgeContext,
                        SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext);

    void afterFinally(SfEdgeContext<NI, EI, EEI, EC> edgeContext,
                      SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext);

    void afterCancel(SfEdgeContext<NI, EI, EEI, EC> edgeContext,
                     SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext);

}
