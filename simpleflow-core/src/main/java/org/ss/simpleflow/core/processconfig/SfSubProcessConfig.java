package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.node.SfNodeConfig;

import java.io.Serializable;
import java.util.Set;

public interface SfSubProcessConfig extends Serializable {

    String getId();

    Set<SfNodeConfig> getNodeConfigList();

    Set<SfLineConfig> getLineConfigList();
}
