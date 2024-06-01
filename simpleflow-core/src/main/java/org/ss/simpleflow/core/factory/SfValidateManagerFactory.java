package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.validate.SfLineConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;
import org.ss.simpleflow.core.validate.SfValidateManager;

public interface SfValidateManagerFactory {

    SfValidateManager buildValidateManager(SfNodeConfigCustomValidator nodeConfigCustomValidator,
                                           SfLineConfigCustomValidator lineConfigCustomValidator,
                                           SfProcessConfigCustomValidate processConfigCustomValidate);

}
