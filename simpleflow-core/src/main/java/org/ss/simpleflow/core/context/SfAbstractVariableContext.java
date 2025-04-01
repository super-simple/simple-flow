package org.ss.simpleflow.core.context;

import java.util.HashMap;
import java.util.Map;

public abstract class SfAbstractVariableContext implements SfVariableContext {

    private final Map<String, Object> variables = new HashMap<>();

    @Override
    public void setVariable(String key, Object value) {
        variables.put(key, value);
    }

    @Override
    public void setVariables(Map<String, Object> variables) {
        this.variables.putAll(variables);
    }

    @Override
    public Object getVariable(String key) {
        return variables.get(key);
    }

    @Override
    public Object removeVariable(String key) {
        return variables.remove(key);
    }

    @Override
    public Map<String, Object> getVariables() {
        return variables;
    }

}
