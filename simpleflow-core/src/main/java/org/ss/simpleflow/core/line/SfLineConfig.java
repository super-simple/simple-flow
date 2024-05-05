package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SfComponentConfig;

public interface SfLineConfig extends SfComponentConfig {

    String getId();

    String getFromNodeId();

    String getToNodeId();

    String getLineType();

    String getFromResultKey();

    String getToParameterKey();
}
