package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowExecutionIdGenerator;
import org.ss.simpleflow.core.SimpleFlowProcessEngine;
import org.ss.simpleflow.core.SimpleFlowWorkDispatcher;
import org.ss.simpleflow.core.impl.factory.SimpleFlowEventFactory;
import org.ss.simpleflow.core.impl.factory.SimpleFlowLineFactory;
import org.ss.simpleflow.core.impl.factory.SimpleFlowNodeFactory;

public interface SimpleFlowProcessEngineFactory {

    SimpleFlowProcessEngine buildProcessEngine(SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory, SimpleFlowWorkDispatcher simpleFlowWorkDispatcher, SimpleFlowExecutionIdGenerator executionIdGenerator);

}
