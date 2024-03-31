package org.ss.simpleflow.core.line;

public interface SimpleFlowLineContext {

    SimpleFlowLineConfig getConfig();

    void putVariable(String key, Object value);

    Object getVariable(String key);
}
