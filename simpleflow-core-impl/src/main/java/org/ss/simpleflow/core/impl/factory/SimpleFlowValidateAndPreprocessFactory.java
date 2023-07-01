package org.ss.simpleflow.core.impl.factory;

import org.ss.simpleflow.core.SimpleFlowValidateAndPreprocess;
import org.ss.simpleflow.core.impl.SimpleFlowAbstractLineConfigValidator;
import org.ss.simpleflow.core.impl.SimpleFlowAbstractNodeConfigValidator;

public interface SimpleFlowValidateAndPreprocessFactory {

    SimpleFlowValidateAndPreprocess buildValidateAndPreprocess(SimpleFlowAbstractNodeConfigValidator nodeConfigValidator,
                                                               SimpleFlowAbstractLineConfigValidator lineConfigValidator);

}
