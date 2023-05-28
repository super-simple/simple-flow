package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SimpleFlowComponentConfig;

public interface SimpleFlowLineConfig extends SimpleFlowComponentConfig {

    String getFromNodeId();

    String getToNodeId();

}
