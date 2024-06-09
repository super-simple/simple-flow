package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

public interface SfNodeConfigCustomValidator<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>> {
    void validateSingleNodeConfig(NODE_CONFIG nodeConfig);
}
