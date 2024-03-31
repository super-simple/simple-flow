package org.ss.simpleflow.core.impl.processengine;

import org.ss.simpleflow.core.factory.*;
import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;
import org.ss.simpleflow.core.processengine.SimpleFlowProcessEngine;
import org.ss.simpleflow.core.processengine.SimpleFlowProcessEngineConfig;
import org.ss.simpleflow.core.validate.SimpleFlowTrimProcessConfig;
import org.ss.simpleflow.core.validate.SimpleFlowValidateManager;

public class SimpleFlowProcessEngineImpl implements SimpleFlowProcessEngine {

    private final SimpleFlowProcessEngineConfig processEngineConfig;
    private final SimpleFlowEventFactory eventFactory;
    private final SimpleFlowNodeFactory nodeFactory;
    private final SimpleFlowLineFactory lineFactory;
    private final SimpleFlowEnumGatewayFactory enumGatewayFactory;
    private final SimpleFlowIteratorFactory iteratorFactory;
    private final SimpleFlowGatewayFactory gatewayFactory;
    private final SimpleFlowTrimProcessConfig trimProcessConfig;
    private final SimpleFlowValidateManager validateManager;

    public SimpleFlowProcessEngineImpl(SimpleFlowProcessEngineConfig processEngineConfig,
                                       SimpleFlowEventFactory eventFactory,
                                       SimpleFlowNodeFactory nodeFactory,
                                       SimpleFlowLineFactory lineFactory,
                                       SimpleFlowEnumGatewayFactory enumGatewayFactory,
                                       SimpleFlowIteratorFactory iteratorFactory,
                                       SimpleFlowGatewayFactory gatewayFactory,
                                       SimpleFlowTrimProcessConfig trimProcessConfig,
                                       SimpleFlowValidateManager validateManager) {
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
    public String runProcess(SimpleFlowProcessConfig processConfig) {
        validateManager.basicValidate(processConfig);
        boolean trim = processEngineConfig.enableTrim();
        SimpleFlowProcessConfig realProcessConfig;
        if (trim) {
            realProcessConfig = trimProcessConfig.trim(processConfig);
        } else {
            realProcessConfig = processConfig;
        }

        return null;
    }
}
