package org.ss.simpleflow.core.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ss.simpleflow.core.SimpleFlowProcessEngine;
import org.ss.simpleflow.core.impl.simple.SimpleFlowSimpleProcessConfig;
import org.ss.simpleflow.core.impl.simple.SimpleFlowSimpleProcessEngineFactory;
import org.ss.simpleflow.core.impl.simple.event.SimpleFlowSimpleEventFactory;
import org.ss.simpleflow.core.impl.simple.line.SimpleFlowSimpleLineFactory;
import org.ss.simpleflow.core.impl.simple.node.SimpleFlowSimpleNodeFactory;

import java.util.concurrent.TimeUnit;

public class MainTest1 {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleFlowSimpleProcessConfig processConfig = objectMapper.readValue(MainTest1.class.getResourceAsStream("/basic/basic1.json"), SimpleFlowSimpleProcessConfig.class);

        SimpleFlowSimpleProcessEngineFactory simpleFlowProcessEngineFactory = new SimpleFlowSimpleProcessEngineFactory();
        SimpleFlowSimpleEventFactory eventFactory = new SimpleFlowSimpleEventFactory();
        SimpleFlowSimpleNodeFactory nodeFactory = new SimpleFlowSimpleNodeFactory();
        SimpleFlowSimpleLineFactory lineFactory = new SimpleFlowSimpleLineFactory();
        SimpleFlowWorkDispatcherImpl workDispatcher = new SimpleFlowWorkDispatcherImpl();
        SimpleFlowExecutionIdGeneratorUUIDImpl executionIdGenerator = new SimpleFlowExecutionIdGeneratorUUIDImpl();
        SimpleFlowProcessEngine simpleFlowProcessEngine = simpleFlowProcessEngineFactory.buildProcessEngine(eventFactory, nodeFactory, lineFactory, workDispatcher, executionIdGenerator);
        String executionId = simpleFlowProcessEngine.runProcess(processConfig);
        TimeUnit.DAYS.sleep(1);
    }
}
