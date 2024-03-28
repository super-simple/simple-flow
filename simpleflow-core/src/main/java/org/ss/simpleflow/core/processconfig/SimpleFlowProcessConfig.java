package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.line.SimpleFlowLineConfig;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

import java.util.List;

public interface SimpleFlowProcessConfig {
    List<SimpleFlowNodeConfig> getNodeConfigList();

    List<SimpleFlowLineConfig> getLineConfigList();
}
