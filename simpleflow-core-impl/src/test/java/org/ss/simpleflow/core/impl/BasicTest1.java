package org.ss.simpleflow.core.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.ss.simpleflow.core.SimpleFlowProcessEngine;

import java.util.concurrent.TimeUnit;

public class BasicTest1 {

    @Test
    void basic1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleFlowProcessConfigImpl simpleFlowProcessConfig = objectMapper.readValue(BasicTest1.class.getResourceAsStream("/basic/basic1.json"), SimpleFlowProcessConfigImpl.class);

        SimpleFlowProcessEngineFactoryImpl simpleFlowProcessEngineFactory = new SimpleFlowProcessEngineFactoryImpl();
        SimpleFlowEventFactoryImpl eventFactory = new SimpleFlowEventFactoryImpl();
        SimpleFlowNodeFactoryImpl nodeFactory = new SimpleFlowNodeFactoryImpl();
        SimpleFlowLineFactoryImpl lineFactory = new SimpleFlowLineFactoryImpl();
        SimpleFlowWorkDispatcherImpl workDispatcher = new SimpleFlowWorkDispatcherImpl();
        SimpleFlowExecutionIdGeneratorUUIDImpl executionIdGenerator = new SimpleFlowExecutionIdGeneratorUUIDImpl();
        SimpleFlowProcessEngine simpleFlowProcessEngine = simpleFlowProcessEngineFactory.buildProcessEngine(eventFactory, nodeFactory, lineFactory, workDispatcher, executionIdGenerator);
        String executionId = simpleFlowProcessEngine.runProcess(simpleFlowProcessConfig);
        System.out.println(executionId);
        TimeUnit.DAYS.sleep(1);
    }
}
