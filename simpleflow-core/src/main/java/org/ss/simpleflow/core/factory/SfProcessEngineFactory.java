package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.aspect.SfEdgeAspect;
import org.ss.simpleflow.core.aspect.SfNodeAspect;
import org.ss.simpleflow.core.aspect.SfProcessAspect;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processengine.SfComponentExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessExecutionIdGenerator;

import java.util.List;

public interface SfProcessEngineFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    SfProcessEngine<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>
    createProcessEngine(SfProcessEngineConfig processEngineConfig,
                        SfControlEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> controlEdgeFactory,
                        SfDataEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> dataEdgeFactory,
                        SfEventFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> eventFactory,
                        SfNodeFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> nodeFactory,
                        SfEnumGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> enumGatewayFactory,
                        SfStreamIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> streamIteratorFactory,
                        SfGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> gatewayFactory,
                        SfAroundIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> aroundIteratorFactory,
                        SfComponentExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> componentExecutionIdGenerator,
                        SfProcessExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, PEI> processExecutionIdGenerator,
                        SfExecutionContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> executionContextFactory,
                        List<SfEdgeAspect<NI, EI, PCI, NC, EC, PC, EEI, PEI>> edgeAspectList,
                        List<SfNodeAspect<NI, EI, PCI, NC, EC, PC, NEI, PEI>> nodeAspectList,
                        List<SfProcessAspect<NI, EI, PCI, NC, EC, PC, PEI>> processAspectList);

}
