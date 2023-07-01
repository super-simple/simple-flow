package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowValidateAndPreprocess;
import org.ss.simpleflow.core.impl.factory.SimpleFlowValidateAndPreprocessFactory;

public class SimpleFlowUniqueValidateAndPreprocessFactory implements SimpleFlowValidateAndPreprocessFactory {
    @Override
    public SimpleFlowValidateAndPreprocess buildValidateAndPreprocess(SimpleFlowAbstractNodeConfigValidator nodeConfigValidator,
                                                                      SimpleFlowAbstractLineConfigValidator lineConfigValidator) {
        return new SimpleFlowUniqueValidateAndPreprocess(nodeConfigValidator, lineConfigValidator);
    }
}
