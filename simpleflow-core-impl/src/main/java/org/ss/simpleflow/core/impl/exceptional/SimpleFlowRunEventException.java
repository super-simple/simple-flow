package org.ss.simpleflow.core.impl.exceptional;

public class SimpleFlowRunEventException extends RuntimeException {

    public SimpleFlowRunEventException() {
    }

    public SimpleFlowRunEventException(String message) {
        super(message);
    }

    public SimpleFlowRunEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleFlowRunEventException(Throwable cause) {
        super(cause);
    }

    public SimpleFlowRunEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
