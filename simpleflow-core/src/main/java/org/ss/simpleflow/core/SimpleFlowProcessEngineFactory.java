package org.ss.simpleflow.core;

public interface SimpleFlowProcessEngineFactory {

    SimpleFlowProcessEngine buildProcessEngine(SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory, SimpleFlowWorkDispatcher simpleFlowWorkDispatcher, SimpleFlowExecutionIdGenerator executionIdGenerator);

}
