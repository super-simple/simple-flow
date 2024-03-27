package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.line.SimpleFlowLineConfig;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

import java.util.List;

public interface SimpleFlowProcessConfig {

    String getId();

    String getCode();

    String getName();

    String getDescription();

    int getVersion();

    List<SimpleFlowNodeConfig> getNodeConfigList();

    List<SimpleFlowLineConfig> getLineConfigList();

}