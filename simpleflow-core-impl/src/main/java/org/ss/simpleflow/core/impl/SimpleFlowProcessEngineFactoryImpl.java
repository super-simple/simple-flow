package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.*;

public class SimpleFlowProcessEngineFactoryImpl implements SimpleFlowProcessEngineFactory {

    @Override
    public SimpleFlowProcessEngine getProcessEngine(
            SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory,
            SimpleFlowWorkDispatcher workThreadPool, SimpleFlowExecutionIdGenerator executionIdGenerator) {
        return new SimpleFlowProcessEngineImpl(eventFactory, nodeFactory, lineFactory, workThreadPool, executionIdGenerator);
    }

}
