package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowExecutionIdGenerator;
import org.ss.simpleflow.core.component.SimpleFlowComponentConfig;
import org.ss.simpleflow.core.constant.SimpleFlowComponentType;
import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;

import java.util.UUID;

public class SimpleFlowExecutionIdGeneratorUUIDImpl implements SimpleFlowExecutionIdGenerator {
    @Override
    public String generateProcessExecutionId(SimpleFlowProcessConfig processConfig, String processId, String processCode) {
        return UUID.randomUUID().toString();
    }

    @Override
    public String generateComponentExecutionId(SimpleFlowProcessConfig processConfig, String id, String code, SimpleFlowComponentType componentType, SimpleFlowComponentConfig componentConfig) {
        return UUID.randomUUID().toString();
    }

}
