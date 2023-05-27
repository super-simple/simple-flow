package org.ss.simpleflow.core;

import java.util.Set;

public interface SimpleFlowProcessConfig {

    String getId();

    String getName();

    String getDescription();

    Set<SimpleFlowNodeConfig> getNodeConfigSet();

    Set<SimpleFlowLineConfig> getLineConfigSet();

}
