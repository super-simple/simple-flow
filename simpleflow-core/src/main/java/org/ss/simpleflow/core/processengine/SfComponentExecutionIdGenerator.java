package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.node.SfNodeConfig;

public interface SfComponentExecutionIdGenerator {

    String generateNodeExecutionId(SfNodeConfig nodeConfig);

    String generateLineExecutionId(SfLineConfig lineConfig);
}
