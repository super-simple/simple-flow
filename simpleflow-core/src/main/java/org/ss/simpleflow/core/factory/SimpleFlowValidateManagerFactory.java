package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfigCustomValidate;
import org.ss.simpleflow.core.validate.*;

public interface SimpleFlowValidateManagerFactory {

    SimpleFlowValidateManager buildValidateManager(SimpleFlowNodeConfigValidator nodeConfigValidator,
                                                   SimpleFlowLineConfigValidator lineConfigValidator,
                                                   SimpleFlowBasicValidate basicValidate,
                                                   SimpleFlowValidateAndPreprocess validateAndPreprocess,
                                                   SimpleFlowNodeConfigCustomValidator nodeConfigCustomValidator,
                                                   SimpleFlowLineConfigCustomValidator lineConfigCustomValidator,
                                                   SimpleFlowProcessConfigCustomValidate processConfigCustomValidate);

}
