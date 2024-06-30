package org.ss.simpleflow.core.context;

import java.util.Map;

public interface SfVariableContext {

    void setVariable(String key, Object value);

    Object getVariable(String key);

    Object removeVariable(String key);

    Map<String, Object> getVariables();
}
