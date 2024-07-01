package org.ss.simpleflow.core.impl.processengine;

import org.ss.simpleflow.core.aspect.SfControlLineAspect;
import org.ss.simpleflow.core.aspect.SfNodeAspect;
import org.ss.simpleflow.core.aspect.SfProcessAspect;
import org.ss.simpleflow.core.factory.*;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfComponentExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessExecutionIdGenerator;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.List;
import java.util.Map;

public class SfProcessEngineImpl<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, LINE_EXECUTION_ID, PROCESS_EXECUTION_ID>
        implements SfProcessEngine<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG
        , NODE_EXECUTION_ID, LINE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    private final SfProcessEngineConfig processEngineConfig;

    private final SfControlLineFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, LINE_EXECUTION_ID, PROCESS_EXECUTION_ID> controlLineFactory;
    private final SfDataLineFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, LINE_EXECUTION_ID, PROCESS_EXECUTION_ID> dataLineFactory;
    private final SfEventFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> eventFactory;
    private final SfNodeFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> nodeFactory;
    private final SfEnumGatewayFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> enumGatewayFactory;
    private final SfStreamIteratorFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> streamIteratorFactory;
    private final SfGatewayFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> gatewayFactory;
    private final SfAroundIteratorFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> aroundIteratorFactory;

    private final SfValidateManager<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH> validateManager;

    private final SfComponentExecutionIdGenerator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, LINE_EXECUTION_ID, PROCESS_EXECUTION_ID> componentExecutionIdGenerator;
    private final SfProcessExecutionIdGenerator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processExecutionIdGenerator;

    private final List<SfControlLineAspect<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, LINE_EXECUTION_ID, PROCESS_EXECUTION_ID>> controlLineAspectList;
    private final List<SfNodeAspect<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID>> nodeAspectList;
    List<SfProcessAspect<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID>> processAspectList;

    SfProcessEngineImpl(SfProcessEngineConfig processEngineConfig,
                        SfControlLineFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, LINE_EXECUTION_ID, PROCESS_EXECUTION_ID> controlLineFactory,
                        SfDataLineFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, LINE_EXECUTION_ID, PROCESS_EXECUTION_ID> dataLineFactory,
                        SfEventFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> eventFactory,
                        SfNodeFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> nodeFactory,
                        SfEnumGatewayFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> enumGatewayFactory,
                        SfStreamIteratorFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> streamIteratorFactory,
                        SfGatewayFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> gatewayFactory,
                        SfAroundIteratorFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> aroundIteratorFactory,
                        SfValidateManager<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH> validateManager,
                        SfComponentExecutionIdGenerator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, LINE_EXECUTION_ID, PROCESS_EXECUTION_ID> componentExecutionIdGenerator,
                        SfProcessExecutionIdGenerator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processExecutionIdGenerator,
                        List<SfControlLineAspect<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, LINE_EXECUTION_ID, PROCESS_EXECUTION_ID>> controlLineAspectList,
                        List<SfNodeAspect<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID>> nodeAspectList,
                        List<SfProcessAspect<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID>> processAspectList) {
        this.processEngineConfig = processEngineConfig;
        this.eventFactory = eventFactory;
        this.nodeFactory = nodeFactory;
        this.controlLineFactory = controlLineFactory;
        this.dataLineFactory = dataLineFactory;
        this.enumGatewayFactory = enumGatewayFactory;
        this.streamIteratorFactory = streamIteratorFactory;
        this.gatewayFactory = gatewayFactory;
        this.aroundIteratorFactory = aroundIteratorFactory;
        this.validateManager = validateManager;
        this.componentExecutionIdGenerator = componentExecutionIdGenerator;
        this.processExecutionIdGenerator = processExecutionIdGenerator;
        this.controlLineAspectList = controlLineAspectList;
        this.nodeAspectList = nodeAspectList;
        this.processAspectList = processAspectList;
    }

    @Override
    public String runProcess(PROCESS_CONFIG processConfig,
                             PROCESS_EXECUTION_ID executionId,
                             Map<String, Object> params,
                             Map<String, Object> env) {
        return null;
    }

    @Override
    public String runProcess(PROCESS_CONFIG processConfig, Map<String, Object> params, Map<String, Object> env) {
        return null;
    }

    @Override
    public String runProcess(PROCESS_CONFIG processConfig, Map<String, Object> params) {
        return null;
    }
}
