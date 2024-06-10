package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.iterator.SfAroundIterator;
import org.ss.simpleflow.core.node.SfNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfAroundIteratorFactory {

    SfAroundIterator createAroundIterator(SfNodeConfig aroundIteratorConfig,
                                          SfProcessConfigGraph processConfigGraph,
                                          SfNodeContext nodeContext);
}
