package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfEdgeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

public class SfEdgeConfigException extends RuntimeException {
    private final SfEdgeConfigExceptionCode exceptionCode;
    private final SfEdgeConfig edgeConfig;
    private final SfProcessContext processContext;
    private final SfProcessEngineConfig processEngineConfig;

    public SfEdgeConfigException(SfEdgeConfigExceptionCode exceptionCode,
                                 SfEdgeConfig edgeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessContext processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        this.exceptionCode = exceptionCode;
        this.edgeConfig = edgeConfig;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfEdgeConfigException(String message,
                                 SfEdgeConfigExceptionCode exceptionCode,
                                 SfEdgeConfig edgeConfig,
                                 SfProcessContext processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.edgeConfig = edgeConfig;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfEdgeConfigException(String message,
                                 Throwable cause,
                                 SfEdgeConfigExceptionCode exceptionCode,
                                 SfEdgeConfig edgeConfig,
                                 SfProcessContext processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.edgeConfig = edgeConfig;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfEdgeConfigException(Throwable cause,
                                 SfEdgeConfigExceptionCode exceptionCode,
                                 SfEdgeConfig edgeConfig,
                                 SfProcessContext processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.edgeConfig = edgeConfig;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfEdgeConfigException(String message,
                                 Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace,
                                 SfEdgeConfigExceptionCode exceptionCode,
                                 SfEdgeConfig edgeConfig,
                                 SfProcessContext processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.edgeConfig = edgeConfig;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

}
