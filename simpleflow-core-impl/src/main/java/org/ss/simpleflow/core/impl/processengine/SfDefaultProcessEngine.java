package org.ss.simpleflow.core.impl.processengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ss.simpleflow.core.aspect.SfEdgeAspect;
import org.ss.simpleflow.core.aspect.SfNodeAspect;
import org.ss.simpleflow.core.aspect.SfProcessAspect;
import org.ss.simpleflow.core.context.SfExecutionProcessContext;
import org.ss.simpleflow.core.context.SfExecutionProcessInternalContext;
import org.ss.simpleflow.core.context.SfExecutionWholeContext;
import org.ss.simpleflow.core.context.SfProcessExecuteResult;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.factory.*;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processengine.SfComponentExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessExecutionIdGenerator;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.List;
import java.util.Map;

public class SfDefaultProcessEngine<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,

        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI>
        implements SfProcessEngine<NI, EI, PCI, NC, EC,
        PC, NEI, EEI, PEI> {

    private static final Logger LOG = LoggerFactory.getLogger(SfDefaultProcessEngine.class);

    private final SfProcessEngineConfig processEngineConfig;

    private final SfControlEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> controlEdgeFactory;
    private final SfDataEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> dataEdgeFactory;
    private final SfEventFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> eventFactory;
    private final SfNodeFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> nodeFactory;
    private final SfEnumGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> enumGatewayFactory;
    private final SfStreamIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> streamIteratorFactory;
    private final SfGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> gatewayFactory;
    private final SfAroundIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> aroundIteratorFactory;

    private final SfValidateManager<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> validateManager;

    private final SfComponentExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> componentExecutionIdGenerator;
    private final SfProcessExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, PEI> processExecutionIdGenerator;

    private final SfContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> contextFactory;

    private final List<SfEdgeAspect<NI, EI, PCI, NC, EC, PC, EEI, PEI>> edgeAspectList;
    private final List<SfNodeAspect<NI, EI, PCI, NC, EC, PC, NEI, PEI>> nodeAspectList;
    private final List<SfProcessAspect<NI, EI, PCI, NC, EC, PC, PEI>> processAspectList;

    SfDefaultProcessEngine(SfProcessEngineConfig processEngineConfig,
                           SfControlEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> controlEdgeFactory,
                           SfDataEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> dataEdgeFactory,
                           SfEventFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> eventFactory,
                           SfNodeFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> nodeFactory,
                           SfEnumGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> enumGatewayFactory,
                           SfStreamIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> streamIteratorFactory,
                           SfGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> gatewayFactory,
                           SfAroundIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> aroundIteratorFactory,
                           SfValidateManager<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> validateManager,
                           SfComponentExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> componentExecutionIdGenerator,
                           SfProcessExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, PEI> processExecutionIdGenerator,
                           SfContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> contextFactory,
                           List<SfEdgeAspect<NI, EI, PCI, NC, EC, PC, EEI, PEI>> edgeAspectList,
                           List<SfNodeAspect<NI, EI, PCI, NC, EC, PC, NEI, PEI>> nodeAspectList,
                           List<SfProcessAspect<NI, EI, PCI, NC, EC, PC, PEI>> processAspectList) {
        if (processEngineConfig == null) {
            throw new IllegalArgumentException("SfProcessEngineConfig can not be null");
        }
        this.processEngineConfig = processEngineConfig;

        if (controlEdgeFactory == null) {
            throw new IllegalArgumentException("SfControlEdgeFactory can not be null");
        }
        this.controlEdgeFactory = controlEdgeFactory;

        if (dataEdgeFactory == null) {
            throw new IllegalArgumentException("SfDataEdgeFactory can not be null");
        }
        this.dataEdgeFactory = dataEdgeFactory;

        if (eventFactory == null) {
            throw new IllegalArgumentException("SfEventFactory can not be null");
        }
        this.eventFactory = eventFactory;

        if (nodeFactory == null) {
            throw new IllegalArgumentException("SfNodeFactory can not be null");
        }
        this.nodeFactory = nodeFactory;

        if (enumGatewayFactory == null) {
            throw new IllegalArgumentException("SfEnumGatewayFactory can not be null");
        }
        this.enumGatewayFactory = enumGatewayFactory;

        if (streamIteratorFactory == null) {
            throw new IllegalArgumentException("SfStreamIteratorFactory can not be null");
        }
        this.streamIteratorFactory = streamIteratorFactory;

        if (gatewayFactory == null) {
            throw new IllegalArgumentException("SfGatewayFactory can not be null");
        }
        this.gatewayFactory = gatewayFactory;

        if (aroundIteratorFactory == null) {
            throw new IllegalArgumentException("SfAroundIteratorFactory can not be null");
        }
        this.aroundIteratorFactory = aroundIteratorFactory;

        if (validateManager == null) {
            throw new IllegalArgumentException("SfValidateManager can not be null");
        }
        this.validateManager = validateManager;

        if (componentExecutionIdGenerator == null) {
            throw new IllegalArgumentException("SfComponentExecutionIdGenerator can not be null");
        }
        this.componentExecutionIdGenerator = componentExecutionIdGenerator;

        if (processExecutionIdGenerator == null) {
            throw new IllegalArgumentException("SfProcessExecutionIdGenerator can not be null");
        }
        this.processExecutionIdGenerator = processExecutionIdGenerator;

        if (contextFactory == null) {
            throw new IllegalArgumentException("SfContextFactory can not be null");
        }
        this.contextFactory = contextFactory;

        this.edgeAspectList = edgeAspectList;
        this.nodeAspectList = nodeAspectList;
        this.processAspectList = processAspectList;
    }

    @Override
    public final SfProcessExecuteResult<PEI> runProcess(SfExecutionWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> wholeContext,
                                                        PEI executionId,
                                                        Map<String, Object> params,
                                                        Map<String, Object> env) {

        SfExecutionProcessContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainExecutionProcessContext = wholeContext.getMainExecutionProcessContext();
        SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> executionInternalContext = mainExecutionProcessContext.getExecutionInternalContext();


        int startNodeConfigIndex = executionInternalContext.getStartNodeConfigIndex();
        List<NC> nodeConfigList = executionInternalContext.getNodeConfigList();
        NC nc = nodeConfigList.get(startNodeConfigIndex);


        return null;
    }

    @Override
    public final SfProcessExecuteResult<PEI> runProcess(SfExecutionWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> wholeContext,
                                                        Map<String, Object> params,
                                                        Map<String, Object> env) {
        return runProcess(wholeContext, null, params, env);
    }

    @Override
    public final SfProcessExecuteResult<PEI> runProcess(SfExecutionWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> wholeContext,
                                                        Map<String, Object> params) {
        return runProcess(wholeContext, params, null);
    }


    private void initContext(SfExecutionWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> wholeContext) {
    }

}
