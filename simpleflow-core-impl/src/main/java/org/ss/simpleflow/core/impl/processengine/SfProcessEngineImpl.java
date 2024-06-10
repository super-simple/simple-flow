package org.ss.simpleflow.core.impl.processengine;

import org.ss.simpleflow.core.factory.*;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.Map;

public class SfProcessEngineImpl<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>>
        implements SfProcessEngine<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG> {

    private final SfProcessEngineConfig processEngineConfig;
    private final SfControlLineFactory controlLineFactory;
    private final SfDataLineFactory dataLineFactory;
    private final SfEventFactory eventFactory;
    private final SfNodeFactory nodeFactory;
    private final SfEnumGatewayFactory enumGatewayFactory;
    private final SfStreamIteratorFactory streamIteratorFactory;
    private final SfGatewayFactory gatewayFactory;
    private final SfAroundIteratorFactory aroundIteratorFactory;
    private final SfValidateManager<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG> validateManager;

    SfProcessEngineImpl(SfProcessEngineConfig processEngineConfig,
                        SfControlLineFactory controlLineFactory,
                        SfDataLineFactory dataLineFactory,
                        SfEventFactory eventFactory,
                        SfNodeFactory nodeFactory,
                        SfEnumGatewayFactory enumGatewayFactory,
                        SfStreamIteratorFactory streamIteratorFactory,
                        SfGatewayFactory gatewayFactory,
                        SfAroundIteratorFactory aroundIteratorFactory,
                        SfValidateManager<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG> validateManager) {
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
    }

    @Override
    public String runProcess(PROCESS_CONFIG processConfig,
                             String executionId,
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
