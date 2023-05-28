package org.ss.simpleflow.core.impl.exceptional;

public class SimpleFlowRunNodeException extends RuntimeException {

    public SimpleFlowRunNodeException() {
    }

    public SimpleFlowRunNodeException(String message) {
        super(message);
    }

    public SimpleFlowRunNodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleFlowRunNodeException(Throwable cause) {
        super(cause);
    }

    public SimpleFlowRunNodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
