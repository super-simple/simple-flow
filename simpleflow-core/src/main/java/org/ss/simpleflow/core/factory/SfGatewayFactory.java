package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.gateway.SfGateway;
import org.ss.simpleflow.core.node.SfNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfGatewayFactory {
    SfGateway createGateway(SfProcessConfigGraph processConfigGraph,
                            SfNodeConfig gatewayConfig,
                            SfNodeContext nodeContext);
}
