package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.line.SimpleFlowAbstractLineConfig;
import org.ss.simpleflow.core.node.SimpleFlowAbstractNodeConfig;

import java.util.Set;

public interface SimpleFlowProcessConfig {

    String getId();

    String getCode();

    String getName();

    String getDescription();

    Set<? extends SimpleFlowAbstractNodeConfig> getNodeConfigSet();

    Set<? extends SimpleFlowAbstractLineConfig> getLineConfigSet();

}
