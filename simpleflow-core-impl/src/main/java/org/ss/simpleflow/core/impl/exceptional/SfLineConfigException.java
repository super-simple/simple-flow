package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.line.SfLineConfig;

public class SfLineConfigException extends RuntimeException {
    private final SfLineConfigExceptionCode exceptionCode;
    private final SfLineConfig lineConfig;

    public SfLineConfigException(SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig) {
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
    }

    public SfLineConfigException(String message,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
    }

    public SfLineConfigException(String message, Throwable cause,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
    }

    public SfLineConfigException(Throwable cause,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
    }

    public SfLineConfigException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig) {
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
