package org.ss.simpleflow.core.impl.exceptional;

public class SimpleFlowRunLineException extends RuntimeException {

    public SimpleFlowRunLineException() {
    }

    public SimpleFlowRunLineException(String message) {
        super(message);
    }

    public SimpleFlowRunLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleFlowRunLineException(Throwable cause) {
        super(cause);
    }

    public SimpleFlowRunLineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
