package org.ss.simpleflow.core.impl.exceptional;

public enum SfNodeConfigExceptionCode {
    WRONG_NODE_TYPE("wrong node type"),
    NO_GATEWAY_ENUM_SET("gateway config don't config enumSet"),
    NO_PROCESS_ID("process node config don't config processId"),
    NO_EVENT_TYPE("event node config don't config event type"),
    NO_NODE_TYPE("node config don't config nodeType"),
    NO_EVENT_CODE("event node config don't config event code"),
    NO_NODE_ID("node config don't config id");

    private final String msg;

    SfNodeConfigExceptionCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
