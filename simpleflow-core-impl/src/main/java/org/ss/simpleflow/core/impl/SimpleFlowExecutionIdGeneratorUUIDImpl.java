package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowExecutionIdGenerator;
import org.ss.simpleflow.core.SimpleFlowProcessConfig;

import java.util.UUID;

public class SimpleFlowExecutionIdGeneratorUUIDImpl implements SimpleFlowExecutionIdGenerator {
    @Override
    public String generateExecutionId(SimpleFlowProcessConfig processConfig) {
        return UUID.randomUUID().toString();
    }
}
