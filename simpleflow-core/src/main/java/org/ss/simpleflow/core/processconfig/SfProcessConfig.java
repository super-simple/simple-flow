package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.node.SfNodeConfig;

import java.util.List;

public interface SfProcessConfig {

    String getId();

    List<SfNodeConfig> getNodeConfigList();

    List<SfLineConfig> getLineConfigList();

    List<SfProcessConfig> getProcessConfigList();
}
