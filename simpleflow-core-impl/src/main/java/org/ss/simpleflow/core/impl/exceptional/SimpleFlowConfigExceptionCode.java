package org.ss.simpleflow.core.impl.exceptional;

public enum SimpleFlowConfigExceptionCode {
    NO_ID("NO_ID", "no id"),
    ID_REPEAT("ID_REPEAT", "id repeat"),
    NO_START_EVENT("NO_START_EVENT", "no start event"),
    MULTI_START_EVENT("MULTI_START_EVENT", "multi start event"),
    ERROR_NODE_TYPE("ERROR_NODE_TYPE", "error node type"),
    ONLY_ONE_START_EVENT("ONLY_ONE_START_EVENT", "only one start event");

    private final String code;
    private final String msg;

    SimpleFlowConfigExceptionCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "SimpleFlowConfigException{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + '}';
    }
}
