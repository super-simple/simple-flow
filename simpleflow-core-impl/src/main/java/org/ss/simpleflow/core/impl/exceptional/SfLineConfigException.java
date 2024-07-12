package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public class SfLineConfigException extends RuntimeException {
    private final SfLineConfigExceptionCode exceptionCode;
    private final SfLineConfig lineConfig;
    private final SfProcessConfigGraph processConfigGraph;

    public SfLineConfigException(SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig,
                                 SfProcessConfigGraph processConfigGraph) {
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
        this.processConfigGraph = processConfigGraph;
    }

    public SfLineConfigException(String message,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig,
                                 SfProcessConfigGraph processConfigGraph) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
        this.processConfigGraph = processConfigGraph;
    }

    public SfLineConfigException(String message,
                                 Throwable cause,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig,
                                 SfProcessConfigGraph processConfigGraph) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
        this.processConfigGraph = processConfigGraph;
    }

    public SfLineConfigException(Throwable cause,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig,
                                 SfProcessConfigGraph processConfigGraph) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
        this.processConfigGraph = processConfigGraph;
    }

    public SfLineConfigException(String message,
                                 Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig,
                                 SfProcessConfigGraph processConfigGraph) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
        this.processConfigGraph = processConfigGraph;
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
