package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.node.SfNode;
import org.ss.simpleflow.core.node.SfNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfNodeFactory {
    SfNode createNode(SfNodeConfig nodeConfig,
                      SfProcessConfigGraph processConfigGraph,
                      SfNodeContext nodeContext);
}
