package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.edge.SfEdgeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessPreprocessConfig;

public class SfEdgeConfigException extends RuntimeException {
    private final SfEdgeConfigExceptionCode exceptionCode;
    private final SfEdgeConfig edgeConfig;
    private final SfProcessConfig processConfig;
    private final SfProcessPreprocessConfig processPreprocessConfig;

    public SfEdgeConfigException(SfEdgeConfigExceptionCode exceptionCode,
                                 SfEdgeConfig edgeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessPreprocessConfig processPreprocessConfig) {
        this.exceptionCode = exceptionCode;
        this.edgeConfig = edgeConfig;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfEdgeConfigException(String message,
                                 SfEdgeConfigExceptionCode exceptionCode,
                                 SfEdgeConfig edgeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessPreprocessConfig processPreprocessConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.edgeConfig = edgeConfig;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfEdgeConfigException(String message,
                                 Throwable cause,
                                 SfEdgeConfigExceptionCode exceptionCode,
                                 SfEdgeConfig edgeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessPreprocessConfig processPreprocessConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.edgeConfig = edgeConfig;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfEdgeConfigException(Throwable cause,
                                 SfEdgeConfigExceptionCode exceptionCode,
                                 SfEdgeConfig edgeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessPreprocessConfig processPreprocessConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.edgeConfig = edgeConfig;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfEdgeConfigException(String message,
                                 Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace,
                                 SfEdgeConfigExceptionCode exceptionCode,
                                 SfEdgeConfig edgeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessPreprocessConfig processPreprocessConfig) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.edgeConfig = edgeConfig;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

}
