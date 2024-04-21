package org.ss.simpleflow.core.impl.exceptional;

public enum SfNodeConfigExceptionCode {
    NO_GATEWAY_ENUM_SET("gateway config don't config enumSet"),
    NO_EVENT_TYPE("event config don't config event type"),
    NO_EVENT_CODE("event config don't config event code"),
    NO_NODE_ID("node config don't config id");

    private final String msg;

    SfNodeConfigExceptionCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
