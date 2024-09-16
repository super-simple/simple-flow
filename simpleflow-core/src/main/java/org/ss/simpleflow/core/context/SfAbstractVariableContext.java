package org.ss.simpleflow.core.context;

import java.util.HashMap;
import java.util.Map;

public abstract class SfAbstractVariableContext implements SfVariableContext {

    private Map<String, Object> variables;

    @Override
    public void setVariable(String key, Object value) {
        if (variables == null) {
            variables = new HashMap<>();
        }
        variables.put(key, value);
    }

    @Override
    public Object getVariable(String key) {
        if (variables == null) {
            return null;
        } else {
            return variables.get(key);
        }
    }

    @Override
    public Object removeVariable(String key) {
        if (variables == null) {
            return null;
        } else {
            return variables.remove(key);
        }
    }

    @Override
    public Map<String, Object> getVariables() {
        if (variables == null) {
            variables = new HashMap<>();
        }
        return variables;
    }

}
