package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.node.SimpleFlowAbstractNodeConfig;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfigValidator;

public abstract class SimpleFlowAbstractNodeConfigValidator implements SimpleFlowNodeConfigValidator {

    @Override
    public final void validateNodeConfig(SimpleFlowAbstractNodeConfig simpleFlowAbstractNodeConfig) {
        doValidate(simpleFlowAbstractNodeConfig);
        customValidate(simpleFlowAbstractNodeConfig);
    }

    private void doValidate(SimpleFlowAbstractNodeConfig simpleFlowAbstractNodeConfig) {

    }

    public abstract void customValidate(SimpleFlowAbstractNodeConfig simpleFlowAbstractNodeConfig);
}
