package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowExecutionIdGenerator;
import org.ss.simpleflow.core.SimpleFlowProcessEngine;
import org.ss.simpleflow.core.SimpleFlowValidateAndPreprocess;
import org.ss.simpleflow.core.SimpleFlowWorkDispatcher;
import org.ss.simpleflow.core.impl.factory.SimpleFlowEventFactory;
import org.ss.simpleflow.core.impl.factory.SimpleFlowLineFactory;
import org.ss.simpleflow.core.impl.factory.SimpleFlowNodeFactory;
import org.ss.simpleflow.core.impl.factory.SimpleFlowProcessEngineFactory;

public class SimpleFlowUniqueProcessEngineFactory implements SimpleFlowProcessEngineFactory {

    @Override
    public SimpleFlowProcessEngine buildProcessEngine(
            SimpleFlowAbstractNodeConfigValidator nodeConfigValidator,
            SimpleFlowAbstractLineConfigValidator lineConfigValidator,
            SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory,
            SimpleFlowWorkDispatcher workDispatcher, SimpleFlowExecutionIdGenerator executionIdGenerator) {
        SimpleFlowUniqueValidateAndPreprocessFactory validateAndPreprocessFactory = new SimpleFlowUniqueValidateAndPreprocessFactory();
        SimpleFlowValidateAndPreprocess simpleFlowValidateAndPreprocess = validateAndPreprocessFactory.buildValidateAndPreprocess(nodeConfigValidator, lineConfigValidator);
        return new SimpleFlowProcessEngineImpl(simpleFlowValidateAndPreprocess, eventFactory, nodeFactory, lineFactory, workDispatcher, executionIdGenerator);
    }

}
