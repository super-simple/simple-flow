package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessPreprocessConfig;

public class SfProcessConfigException extends RuntimeException {
    private final SfProcessConfigExceptionCode exceptionCode;
    private final SfProcessConfig processConfig;
    private final SfProcessPreprocessConfig processPreprocessConfig;

    public SfProcessConfigException(SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfig processConfig,
                                    SfProcessPreprocessConfig processPreprocessConfig) {
        this.exceptionCode = exceptionCode;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfProcessConfigException(String message,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfig processConfig,
                                    SfProcessPreprocessConfig processPreprocessConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfProcessConfigException(String message,
                                    Throwable cause,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfig processConfig,
                                    SfProcessPreprocessConfig processPreprocessConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfProcessConfigException(Throwable cause,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfig processConfig,
                                    SfProcessPreprocessConfig processPreprocessConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfProcessConfigException(String message,
                                    Throwable cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfig processConfig,
                                    SfProcessPreprocessConfig processPreprocessConfig) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

}
