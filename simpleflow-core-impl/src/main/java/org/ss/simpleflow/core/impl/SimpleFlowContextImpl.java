package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleFlowContextImpl implements SimpleFlowContext {

    private Map<String, Object> contextMap = new ConcurrentHashMap<>();

    @Override
    public void putVariable(String variableName, Object value) {
        contextMap.put(variableName, value);
    }

    @Override
    public <T> T getVariable(String variableName) {
        return (T) contextMap.get(variableName);
    }
}
