package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.event.SfEvent;
import org.ss.simpleflow.core.node.SfNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfEventFactory {

    SfEvent createEvent(SfNodeConfig eventConfig,
                        SfProcessConfigGraph processConfigGraph,
                        SfNodeContext nodeContext);

}
