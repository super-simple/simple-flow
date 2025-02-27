package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

public class SfProcessConfigException extends RuntimeException {
    private final SfProcessConfigExceptionCode exceptionCode;
    private final SfProcessContext processContext;
    private final SfProcessEngineConfig processEngineConfig;

    public SfProcessConfigException(SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessContext processContext,
                                    SfProcessEngineConfig processEngineConfig) {
        this.exceptionCode = exceptionCode;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfProcessConfigException(String message,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessContext processContext,
                                    SfProcessEngineConfig processEngineConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfProcessConfigException(String message,
                                    Throwable cause,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessContext processContext,
                                    SfProcessEngineConfig processEngineConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfProcessConfigException(Throwable cause,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessContext processContext,
                                    SfProcessEngineConfig processEngineConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

    public SfProcessConfigException(String message,
                                    Throwable cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessContext processContext,
                                    SfProcessEngineConfig processEngineConfig) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.processContext = processContext;
        this.processEngineConfig = processEngineConfig;
    }

}
