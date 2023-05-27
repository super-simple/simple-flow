package org.ss.simpleflow.core.impl;

public class SimpleFlowProcessRuntimeException extends RuntimeException {
    public SimpleFlowProcessRuntimeException() {
    }

    public SimpleFlowProcessRuntimeException(String message) {
        super(message);
    }

    public SimpleFlowProcessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleFlowProcessRuntimeException(Throwable cause) {
        super(cause);
    }

    public SimpleFlowProcessRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
