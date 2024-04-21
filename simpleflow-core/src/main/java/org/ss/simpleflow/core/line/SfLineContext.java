package org.ss.simpleflow.core.line;

public interface SfLineContext {

    SfLineConfig getConfig();

    void putVariable(String key, Object value);

    Object getVariable(String key);
}
