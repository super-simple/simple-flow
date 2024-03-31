package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public interface SimpleFlowNodeConfigValidator {
    void validateSingleNodeConfig(SimpleFlowNodeConfig nodeConfig);
}
