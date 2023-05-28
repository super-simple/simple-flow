package org.ss.simpleflow.core.impl.simple;

import org.ss.simpleflow.core.SimpleFlowExecutionIdGenerator;
import org.ss.simpleflow.core.SimpleFlowProcessEngine;
import org.ss.simpleflow.core.SimpleFlowWorkDispatcher;
import org.ss.simpleflow.core.impl.SimpleFlowProcessEngineImpl;
import org.ss.simpleflow.core.impl.factory.SimpleFlowEventFactory;
import org.ss.simpleflow.core.impl.factory.SimpleFlowLineFactory;
import org.ss.simpleflow.core.impl.factory.SimpleFlowNodeFactory;
import org.ss.simpleflow.core.impl.factory.SimpleFlowProcessEngineFactory;

public class SimpleFlowSimpleProcessEngineFactory implements SimpleFlowProcessEngineFactory {

    @Override
    public SimpleFlowProcessEngine buildProcessEngine(
            SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory,
            SimpleFlowWorkDispatcher workDispatcher, SimpleFlowExecutionIdGenerator executionIdGenerator) {
        return new SimpleFlowProcessEngineImpl(eventFactory, nodeFactory, lineFactory, workDispatcher, executionIdGenerator);
    }

}
