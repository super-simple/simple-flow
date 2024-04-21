package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.processconfig.SfProcessConfigCustomValidate;
import org.ss.simpleflow.core.validate.*;

public interface SfValidateManagerFactory {

    SfValidateManager buildValidateManager(SfNodeConfigValidator nodeConfigValidator,
                                           SfLineConfigValidator lineConfigValidator,
                                           SfValidateAndPreprocess validateAndPreprocess,
                                           SfNodeConfigCustomValidator nodeConfigCustomValidator,
                                           SfLineConfigCustomValidator lineConfigCustomValidator,
                                           SfProcessConfigCustomValidate processConfigCustomValidate);

}
