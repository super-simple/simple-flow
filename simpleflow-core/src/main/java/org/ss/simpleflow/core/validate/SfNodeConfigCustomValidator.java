package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.node.SfNodeConfig;

public interface SfNodeConfigCustomValidator {
    void validateSingleNodeConfig(SfNodeConfig nodeConfig);
}
