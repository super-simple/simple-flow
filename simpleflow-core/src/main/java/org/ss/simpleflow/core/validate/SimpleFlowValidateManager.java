package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;

public interface SimpleFlowValidateManager {

    void basicValidate(SimpleFlowProcessConfig processConfig);

    void graphValidate(SimpleFlowProcessConfig processConfig);

    void businessValidate(SimpleFlowProcessConfig processConfig);

}
