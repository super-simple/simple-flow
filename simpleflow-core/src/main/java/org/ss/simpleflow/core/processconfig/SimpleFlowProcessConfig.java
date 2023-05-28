package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.line.SimpleFlowLineConfig;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

import java.util.Set;

public interface SimpleFlowProcessConfig {

    String getId();

    String getCode();

    String getName();

    String getDescription();

    Set<? extends SimpleFlowNodeConfig> getNodeConfigSet();

    Set<? extends SimpleFlowLineConfig> getLineConfigSet();

}
