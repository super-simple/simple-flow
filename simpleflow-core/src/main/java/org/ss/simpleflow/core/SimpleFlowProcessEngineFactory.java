package org.ss.simpleflow.core;

import org.ss.simpleflow.core.event.SimpleFlowEventFactory;
import org.ss.simpleflow.core.line.SimpleFlowLineFactory;
import org.ss.simpleflow.core.node.SimpleFlowNodeFactory;

public interface SimpleFlowProcessEngineFactory {

    SimpleFlowProcessEngine buildProcessEngine(SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory, SimpleFlowWorkDispatcher simpleFlowWorkDispatcher, SimpleFlowExecutionIdGenerator executionIdGenerator);

}
