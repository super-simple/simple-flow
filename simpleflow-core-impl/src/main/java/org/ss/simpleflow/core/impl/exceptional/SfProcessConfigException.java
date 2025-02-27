package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

public class SfProcessConfigException extends RuntimeException {
    private final SfProcessConfigExceptionCode exceptionCode;
    private final SfProcessConfig processConfig;
    private final SfProcessEngineConfig processEngineConfig;

    public SfProcessConfigException(SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfig processConfig,
                                    SfProcessEngineConfig processEngineConfig) {
        this.exceptionCode = exceptionCode;
        this.processConfig = processConfig;
        this.processEngineConfig = processEngineConfig;
    }

    public SfProcessConfigException(String message,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfig processConfig,
                                    SfProcessEngineConfig processEngineConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.processConfig = processConfig;
        this.processEngineConfig = processEngineConfig;
    }

    public SfProcessConfigException(String message,
                                    Throwable cause,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfig processConfig,
                                    SfProcessEngineConfig processEngineConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.processConfig = processConfig;
        this.processEngineConfig = processEngineConfig;
    }

    public SfProcessConfigException(Throwable cause,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfig processConfig,
                                    SfProcessEngineConfig processEngineConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.processConfig = processConfig;
        this.processEngineConfig = processEngineConfig;
    }

    public SfProcessConfigException(String message,
                                    Throwable cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfig processConfig,
                                    SfProcessEngineConfig processEngineConfig) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.processConfig = processConfig;
        this.processEngineConfig = processEngineConfig;
    }

}
