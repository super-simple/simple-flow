package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.aspect.SfEdgeAspect;
import org.ss.simpleflow.core.aspect.SfNodeAspect;
import org.ss.simpleflow.core.aspect.SfProcessAspect;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfComponentExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfSyncProcessEngine;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.List;

public interface SfSyncProcessEngineFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    SfSyncProcessEngine<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>
    createSyncProcessEngine(SfProcessEngineConfig processEngineConfig,
                            SfControlEdgeFactory<NI, EI, PCI, NC, EC, PCG, PC, EEI, PEI> controlEdgeFactory,
                            SfDataEdgeFactory<NI, EI, PCI, NC, EC, PCG, PC, EEI, PEI> dataEdgeFactory,
                            SfEventFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> eventFactory,
                            SfNodeFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> nodeFactory,
                            SfEnumGatewayFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> enumGatewayFactory,
                            SfStreamIteratorFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> streamIteratorFactory,
                            SfGatewayFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> gatewayFactory,
                            SfAroundIteratorFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI> aroundIteratorFactory,
                            SfValidateManager<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validateManager,
                            SfComponentExecutionIdGenerator<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> componentExecutionIdGenerator,
                            SfProcessExecutionIdGenerator<NI, EI, PCI, NC, EC, PCG, PC, PEI> processExecutionIdGenerator,
                            SfContextFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> contextFactory,
                            List<SfEdgeAspect<NI, EI, PCI, NC, EC, PCG, PC, EEI, PEI>> edgeAspectList,
                            List<SfNodeAspect<NI, EI, PCI, NC, EC, PCG, PC, NEI, PEI>> nodeAspectList,
                            List<SfProcessAspect<NI, EI, PCI, NC, EC, PCG, PC, PEI>> processAspectList);

}
