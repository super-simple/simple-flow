package org.ss.simpleflow.core;


public interface SimpleFlowContext {
    void putVariable(String variableName, Object value);

    <T> T getVariable(String variableName);

}
