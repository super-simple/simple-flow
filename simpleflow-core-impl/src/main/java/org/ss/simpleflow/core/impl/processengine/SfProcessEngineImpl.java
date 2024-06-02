package org.ss.simpleflow.core.impl.processengine;

import org.ss.simpleflow.core.factory.*;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.Map;

public class SfProcessEngineImpl implements SfProcessEngine {

    private final SfProcessEngineConfig processEngineConfig;
    private final SfControlLineFactory controlLineFactory;
    private final SfDataLineFactory dataLineFactory;
    private final SfEventFactory eventFactory;
    private final SfNodeFactory nodeFactory;
    private final SfEnumGatewayFactory enumGatewayFactory;
    private final SfStreamIteratorFactory streamIteratorFactory;
    private final SfGatewayFactory gatewayFactory;
    private final SfAroundIteratorFactory aroundIteratorFactory;
    private final SfValidateManager validateManager;

    SfProcessEngineImpl(SfProcessEngineConfig processEngineConfig,
                        SfControlLineFactory controlLineFactory,
                        SfDataLineFactory dataLineFactory,
                        SfEventFactory eventFactory,
                        SfNodeFactory nodeFactory,
                        SfEnumGatewayFactory enumGatewayFactory,
                        SfStreamIteratorFactory streamIteratorFactory,
                        SfGatewayFactory gatewayFactory,
                        SfAroundIteratorFactory aroundIteratorFactory,
                        SfValidateManager validateManager) {
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
    public String runProcess(SfProcessConfig processConfig,
                             String executionId,
                             Map<String, Object> params,
                             Map<String, Object> env) {
        return null;
    }

    @Override
    public String runProcess(SfProcessConfig processConfig, Map<String, Object> params, Map<String, Object> env) {
        return null;
    }

    @Override
    public String runProcess(SfProcessConfig processConfig, Map<String, Object> params) {
        return null;
    }
}
