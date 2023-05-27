package org.ss.simpleflow.core.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class BasicTest1 {

    @Test
    void basic1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleFlowProcessConfigImpl simpleFlowProcessConfig = objectMapper.readValue(BasicTest1.class.getResourceAsStream("basic/basic1.json"), SimpleFlowProcessConfigImpl.class);

        SimpleFlowProcessEngineFactoryImpl simpleFlowProcessEngineFactory = new SimpleFlowProcessEngineFactoryImpl();
    }
}
