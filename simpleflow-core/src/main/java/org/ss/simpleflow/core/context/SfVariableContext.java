package org.ss.simpleflow.core.context;

import java.io.Serializable;
import java.util.Map;

public interface SfVariableContext extends Serializable {

    void setVariable(String key, Object value);

    void setVariables(Map<String, Object> variables);

    Object getVariable(String key);

    Object removeVariable(String key);

    Map<String, Object> getVariables();
}
