package org.ss.simpleflow.core.impl.exceptional;

public enum SfLineConfigExceptionCode {
    WRONG_LINE_TYPE("wrong line type"),
    NO_LINE_TYPE("line config don't config line type"),
    NO_LINE_ID("line config don't config id");

    private final String msg;

    SfLineConfigExceptionCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
