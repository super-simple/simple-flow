package org.ss.simpleflow.core.component;

import org.ss.simpleflow.core.line.SimpleFlowLineConfig;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public interface SimpleFlowComponentExecutionIdGenerator {

    String generateNodeExecutionId(SimpleFlowNodeConfig nodeConfig);

    String generateLineExecutionId(SimpleFlowLineConfig lineConfig);
}
