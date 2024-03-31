package org.ss.simpleflow.core.impl.exceptional;

public enum SimpleFlowLineConfigExceptionCode {
    NO_LINE_ID("line config don't config id");

    private final String msg;

    SimpleFlowLineConfigExceptionCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
