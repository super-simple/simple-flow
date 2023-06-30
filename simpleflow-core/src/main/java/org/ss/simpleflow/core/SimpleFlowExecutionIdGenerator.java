package org.ss.simpleflow.core;

import org.ss.simpleflow.core.component.SimpleFlowComponentConfig;
import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;

public interface SimpleFlowExecutionIdGenerator {

    String generateProcessExecutionId(SimpleFlowProcessConfig processConfig,
                                      String processId, String processCode);

    String generateComponentExecutionId(SimpleFlowProcessConfig processConfig,
                                        SimpleFlowComponentConfig componentConfig);

}
