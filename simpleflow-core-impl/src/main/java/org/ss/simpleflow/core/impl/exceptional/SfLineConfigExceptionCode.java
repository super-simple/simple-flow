package org.ss.simpleflow.core.impl.exceptional;

public enum SfLineConfigExceptionCode {
    WRONG_LINE_TYPE("wrong line type"),
    NO_FROM_NODE_ID("line config don't config fromNodeId"),
    NO_TO_NODE_ID("line config don't config toNodeId"),
    NO_FROM_RESULT_KEY("data line config don't config fromResultKey"),
    NO_TO_PARAMETER_KEY("data line config don't config toParameterKey"),
    NO_LINE_TYPE("line config don't config lineType"),
    NO_LINE_ID("line config don't config id");

    private final String msg;

    SfLineConfigExceptionCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
