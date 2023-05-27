package org.ss.simpleflow.core;

public interface SimpleFlowProcessEngineFactory {

    SimpleFlowProcessEngine getProcessEngine(SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory, SimpleFlowWorkDispatcher simpleFlowWorkDispatcher,SimpleFlowExecutionIdGenerator executionIdGenerator);

}
