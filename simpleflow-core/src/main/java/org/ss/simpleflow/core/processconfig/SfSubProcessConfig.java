package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.node.SfNodeConfig;

import java.io.Serializable;
import java.util.List;

public interface SfSubProcessConfig extends Serializable {

    String getId();

    List<SfNodeConfig> getNodeConfigList();

    List<SfLineConfig> getLineConfigList();
}
