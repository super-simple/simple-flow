package org.ss.simpleflow.core.impl.processengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.aspect.SfEdgeAspect;
import org.ss.simpleflow.core.aspect.SfNodeAspect;
import org.ss.simpleflow.core.aspect.SfProcessAspect;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.context.SfProcessReturn;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.factory.*;
import org.ss.simpleflow.core.impl.validate.SfDefaultOrphanComponentCleaner;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfComponentExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessExecutionIdGenerator;
import org.ss.simpleflow.core.validate.SfOrphanComponentCleaner;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.List;
import java.util.Map;

public class SfDefaultProcessEngine<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfAbstractProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>
        implements SfProcessEngine<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH,
        PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    private static final Logger LOG = LoggerFactory.getLogger(SfDefaultProcessEngine.class);

    private final SfProcessEngineConfig processEngineConfig;

    private final SfControlEdgeFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> controlEdgeFactory;
    private final SfDataEdgeFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> dataEdgeFactory;
    private final SfEventFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> eventFactory;
    private final SfNodeFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> nodeFactory;
    private final SfEnumGatewayFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> enumGatewayFactory;
    private final SfStreamIteratorFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> streamIteratorFactory;
    private final SfGatewayFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> gatewayFactory;
    private final SfAroundIteratorFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> aroundIteratorFactory;

    private final SfValidateManager<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> validateManager;

    private final SfComponentExecutionIdGenerator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> componentExecutionIdGenerator;
    private final SfProcessExecutionIdGenerator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processExecutionIdGenerator;

    private final SfContextFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> contextFactory;

    private final List<SfEdgeAspect<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>> edgeAspectList;
    private final List<SfNodeAspect<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID>> nodeAspectList;
    private final List<SfProcessAspect<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID>> processAspectList;

    private final SfOrphanComponentCleaner<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> orphanComponentCleaner;

    SfDefaultProcessEngine(SfProcessEngineConfig processEngineConfig,
                           SfControlEdgeFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> controlEdgeFactory,
                           SfDataEdgeFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> dataEdgeFactory,
                           SfEventFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> eventFactory,
                           SfNodeFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> nodeFactory,
                           SfEnumGatewayFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> enumGatewayFactory,
                           SfStreamIteratorFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> streamIteratorFactory,
                           SfGatewayFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> gatewayFactory,
                           SfAroundIteratorFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> aroundIteratorFactory,
                           SfValidateManager<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> validateManager,
                           SfComponentExecutionIdGenerator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> componentExecutionIdGenerator,
                           SfProcessExecutionIdGenerator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processExecutionIdGenerator,
                           SfContextFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> contextFactory,
                           List<SfEdgeAspect<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>> edgeAspectList,
                           List<SfNodeAspect<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID>> nodeAspectList,
                           List<SfProcessAspect<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID>> processAspectList) {
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

        this.orphanComponentCleaner = new SfDefaultOrphanComponentCleaner<>();
    }

    @Override
    public SfProcessReturn<PROCESS_EXECUTION_ID> runProcess(PROCESS_CONFIG processConfig,
                                                            PROCESS_EXECUTION_ID executionId,
                                                            Map<String, Object> params,
                                                            Map<String, Object> env) {
        if (processConfig == null) {
            throw new NullPointerException("processConfig can not be null");
        }

        SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext = contextFactory.createProcessContext();
        processContext.setProcessConfig(processConfig);

        PROCESS_EXECUTION_ID actualExecutionId;
        if (executionId != null) {
            actualExecutionId = executionId;
        } else {
            actualExecutionId = processExecutionIdGenerator.generateProcessExecutionId(processContext);
        }
        processContext.setProcessExecutionId(actualExecutionId);

        if (CollectionUtils.isNotEmpty(processAspectList)) {
            for (SfProcessAspect<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
                    NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH,
                    PROCESS_CONFIG, PROCESS_EXECUTION_ID> processAspect : processAspectList) {
                try {
                    processAspect.before(params, processContext);
                } catch (Exception e) {
                    LOG.error("processAspect occur error", e);
                }
            }
        }
        validateManager.preValidate(processConfig, processContext, processEngineConfig);

        orphanComponentCleaner.cleanOrphanComponent(processConfig);

        validateManager.validate(processConfig, processContext, processEngineConfig);

        return null;
    }

    @Override
    public SfProcessReturn<PROCESS_EXECUTION_ID> runProcess(PROCESS_CONFIG processConfig,
                                                            Map<String, Object> params,
                                                            Map<String, Object> env) {
        return runProcess(processConfig, null, params, env);
    }

    @Override
    public SfProcessReturn<PROCESS_EXECUTION_ID> runProcess(PROCESS_CONFIG processConfig, Map<String, Object> params) {
        return runProcess(processConfig, params, null);
    }
}
