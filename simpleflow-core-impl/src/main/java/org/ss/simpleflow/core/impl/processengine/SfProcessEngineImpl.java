package org.ss.simpleflow.core.impl.processengine;

import org.ss.simpleflow.core.factory.*;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfTrimProcessConfig;
import org.ss.simpleflow.core.validate.SfValidateManager;

public class SfProcessEngineImpl implements SfProcessEngine {

    private final SfProcessEngineConfig processEngineConfig;
    private final SfEventFactory eventFactory;
    private final SfNodeFactory nodeFactory;
    private final SfLineFactory lineFactory;
    private final SfEnumGatewayFactory enumGatewayFactory;
    private final SfStreamIteratorFactory iteratorFactory;
    private final SfGatewayFactory gatewayFactory;
    private final SfTrimProcessConfig trimProcessConfig;
    private final SfValidateManager validateManager;

    public SfProcessEngineImpl(SfProcessEngineConfig processEngineConfig,
                               SfEventFactory eventFactory,
                               SfNodeFactory nodeFactory,
                               SfLineFactory lineFactory,
                               SfEnumGatewayFactory enumGatewayFactory,
                               SfStreamIteratorFactory iteratorFactory,
                               SfGatewayFactory gatewayFactory,
                               SfTrimProcessConfig trimProcessConfig,
                               SfValidateManager validateManager) {
        this.processEngineConfig = processEngineConfig;
        this.eventFactory = eventFactory;
        this.nodeFactory = nodeFactory;
        this.lineFactory = lineFactory;
        this.enumGatewayFactory = enumGatewayFactory;
        this.iteratorFactory = iteratorFactory;
        this.gatewayFactory = gatewayFactory;
        this.trimProcessConfig = trimProcessConfig;
        this.validateManager = validateManager;
    }

    @Override
    public String runProcess(SfProcessConfig processConfig) {
        validateManager.basicValidate(processConfig);
        boolean trim = processEngineConfig.enableTrim();
        SfProcessConfig realProcessConfig;
        if (trim) {
            realProcessConfig = trimProcessConfig.trim(processConfig);
        } else {
            realProcessConfig = processConfig;
        }

        return null;
    }
}
