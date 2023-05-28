package org.ss.simpleflow.core.impl.factory;

import org.ss.simpleflow.core.SimpleFlowExecutionIdGenerator;
import org.ss.simpleflow.core.SimpleFlowProcessEngine;
import org.ss.simpleflow.core.SimpleFlowWorkDispatcher;

public interface SimpleFlowProcessEngineFactory {

    SimpleFlowProcessEngine buildProcessEngine(SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory, SimpleFlowWorkDispatcher simpleFlowWorkDispatcher, SimpleFlowExecutionIdGenerator executionIdGenerator);

}
