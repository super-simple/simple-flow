package org.ss.simpleflow.core.context;

import java.util.Map;

public abstract class SfProcessReturn<PEI> {
    protected PEI executionId;
    protected Map<String, Object> resultMap;
    protected Map<String, Object> env;

    public PEI getExecutionId() {
        return executionId;
    }

    public void setExecutionId(PEI executionId) {
        this.executionId = executionId;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<String, Object> getEnv() {
        return env;
    }

    public void setEnv(Map<String, Object> env) {
        this.env = env;
    }
}
