package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.line.SimpleFlowLineConfig;

public class SimpleFlowLineConfigException extends RuntimeException {
    private final SimpleFlowLineConfigExceptionCode exceptionCode;
    private final SimpleFlowLineConfig lineConfig;

    public SimpleFlowLineConfigException(SimpleFlowLineConfigExceptionCode exceptionCode,
                                         SimpleFlowLineConfig lineConfig) {
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
    }

    public SimpleFlowLineConfigException(String message,
                                         SimpleFlowLineConfigExceptionCode exceptionCode,
                                         SimpleFlowLineConfig lineConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
    }

    public SimpleFlowLineConfigException(String message, Throwable cause,
                                         SimpleFlowLineConfigExceptionCode exceptionCode,
                                         SimpleFlowLineConfig lineConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
    }

    public SimpleFlowLineConfigException(Throwable cause,
                                         SimpleFlowLineConfigExceptionCode exceptionCode,
                                         SimpleFlowLineConfig lineConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
    }

    public SimpleFlowLineConfigException(String message, Throwable cause,
                                         boolean enableSuppression, boolean writableStackTrace,
                                         SimpleFlowLineConfigExceptionCode exceptionCode,
                                         SimpleFlowLineConfig lineConfig) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
    }

    @Override
    public String getMessage() {
        String prefix = "line id[" + lineConfig.getId() + "] " + exceptionCode.name();
        String message = super.getMessage();
        if (message == null) {
            return prefix;
        } else {
            return prefix + ", " + message;
        }
    }

}
