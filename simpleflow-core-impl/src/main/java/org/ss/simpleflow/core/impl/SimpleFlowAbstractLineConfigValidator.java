package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.line.SimpleFlowAbstractLineConfig;
import org.ss.simpleflow.core.line.SimpleFlowLineConfigValidator;

public abstract class SimpleFlowAbstractLineConfigValidator implements SimpleFlowLineConfigValidator {
    @Override
    public final void validateLineConfig(SimpleFlowAbstractLineConfig simpleFlowAbstractLineConfig) {
        doValidate(simpleFlowAbstractLineConfig);
        customValidate(simpleFlowAbstractLineConfig);
    }

    private void doValidate(SimpleFlowAbstractLineConfig simpleFlowAbstractLineConfig) {

    }

    public abstract void customValidate(SimpleFlowAbstractLineConfig simpleFlowAbstractLineConfig);
}
