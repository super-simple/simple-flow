package org.ss.simpleflow.core;

import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;

public interface SimpleFlowProcessExecutionIdGenerator {

    String generateProcessExecutionId(SimpleFlowProcessConfig processConfig);
}
