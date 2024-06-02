package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.iterator.SfStreamIterator;
import org.ss.simpleflow.core.node.SfNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfStreamIteratorFactory {

    SfStreamIterator createStreamIterator(SfProcessConfigGraph processConfigGraph,
                                          SfNodeConfig streamIteratorConfig,
                                          SfNodeContext nodeContext);

}
