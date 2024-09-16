package org.ss.simpleflow.core.context;

import java.util.Map;

public class SfNodeFunctionContext {

    private Map<String, Object> paramsMap;
    private Map<String, Object> resultMap;

    public Map<String, Object> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(Map<String, Object> paramsMap) {
        this.paramsMap = paramsMap;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

}
