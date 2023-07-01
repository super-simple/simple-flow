package org.ss.simpleflow.core.impl.factory;

import org.ss.simpleflow.core.SimpleFlowExecutionIdGenerator;
import org.ss.simpleflow.core.SimpleFlowProcessEngine;
import org.ss.simpleflow.core.SimpleFlowWorkDispatcher;
import org.ss.simpleflow.core.impl.SimpleFlowAbstractLineConfigValidator;
import org.ss.simpleflow.core.impl.SimpleFlowAbstractNodeConfigValidator;

public interface SimpleFlowProcessEngineFactory {

    SimpleFlowProcessEngine buildProcessEngine(SimpleFlowAbstractNodeConfigValidator nodeConfigValidator,
                                               SimpleFlowAbstractLineConfigValidator lineConfigValidator,
                                               SimpleFlowEventFactory eventFactory,
                                               SimpleFlowNodeFactory nodeFactory,
                                               SimpleFlowLineFactory lineFactory,
                                               SimpleFlowWorkDispatcher simpleFlowWorkDispatcher,
                                               SimpleFlowExecutionIdGenerator executionIdGenerator);

}
