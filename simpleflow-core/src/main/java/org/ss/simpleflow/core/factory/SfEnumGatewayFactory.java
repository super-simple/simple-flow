package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.gateway.SfEnumGateway;
import org.ss.simpleflow.core.node.SfNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfEnumGatewayFactory {

    SfEnumGateway createEnumGateway(SfProcessConfigGraph processConfigGraph,
                                    SfNodeConfig enumGatewayConfig,
                                    SfNodeContext nodeContext);
}
