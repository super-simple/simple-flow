package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.*;

public class SimpleFlowProcessEngineFactoryImpl implements SimpleFlowProcessEngineFactory {

    @Override
    public SimpleFlowProcessEngine buildProcessEngine(
            SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory,
            SimpleFlowWorkDispatcher workDispatcher, SimpleFlowExecutionIdGenerator executionIdGenerator) {
        return new SimpleFlowProcessEngineImpl(eventFactory, nodeFactory, lineFactory, workDispatcher, executionIdGenerator);
    }

}
