package org.ss.simpleflow.core.impl;

public enum SimpleFlowConfigExceptionCode {
    NO_START_EVENT("NO_START_EVENT", "no start event"),
    ERROR_NODE_TYPE("ERROR_NODE_TYPE", "error node type");

    private String code;
    private String msg;

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
