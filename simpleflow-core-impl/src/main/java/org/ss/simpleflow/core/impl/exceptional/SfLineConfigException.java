package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

public class SfLineConfigException extends RuntimeException {
    private final SfLineConfigExceptionCode exceptionCode;
    private final SfLineConfig lineConfig;
    private final SfProcessConfig processConfig;
    private final SfProcessConfigGraph processConfigGraph;
    private final SfProcessContext processContext;
    private final SfProcessEngineConfig processEngineConfig;

    public SfLineConfigException(SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessConfigGraph processConfigGraph,
                                 SfProcessContext processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
        this.processConfig = processConfig;
        this.processConfigGraph = processConfigGraph;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfLineConfigException(String message,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessConfigGraph processConfigGraph,
                                 SfProcessContext processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
        this.processConfig = processConfig;
        this.processConfigGraph = processConfigGraph;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfLineConfigException(String message,
                                 Throwable cause,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessConfigGraph processConfigGraph,
                                 SfProcessContext processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
        this.processConfig = processConfig;
        this.processConfigGraph = processConfigGraph;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfLineConfigException(Throwable cause,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessConfigGraph processConfigGraph,
                                 SfProcessContext processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
        this.processConfig = processConfig;
        this.processConfigGraph = processConfigGraph;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfLineConfigException(String message,
                                 Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace,
                                 SfLineConfigExceptionCode exceptionCode,
                                 SfLineConfig lineConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessConfigGraph processConfigGraph,
                                 SfProcessContext processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.lineConfig = lineConfig;
        this.processConfig = processConfig;
        this.processConfigGraph = processConfigGraph;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
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
