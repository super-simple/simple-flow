package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.node.SfNodeConfig;

public interface SfNodeConfigValidator {
    void validateSingleNodeConfig(SfNodeConfig nodeConfig);
}
