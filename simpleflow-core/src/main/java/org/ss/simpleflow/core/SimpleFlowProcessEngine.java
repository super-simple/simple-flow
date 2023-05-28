package org.ss.simpleflow.core;

import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;

public interface SimpleFlowProcessEngine {

    String runProcess(SimpleFlowProcessConfig processConfig);

}
