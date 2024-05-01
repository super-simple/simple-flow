package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.node.SfNodeConfig;

import java.util.Set;

public interface SfProcessInternalConfig {

    String getId();

    Set<SfNodeConfig> getNodeConfigSet();

    Set<SfLineConfig> getLineConfigSet();
}
