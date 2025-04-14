package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.node.SfNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessPreprocessConfig;

public class SfNodeConfigException extends RuntimeException {
    private final SfNodeConfigExceptionCode exceptionCode;
    private final SfNodeConfig nodeConfig;
    private final SfProcessConfig processConfig;
    private final SfProcessPreprocessConfig processPreprocessConfig;

    public SfNodeConfigException(SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessPreprocessConfig processPreprocessConfig) {
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfNodeConfigException(String message,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessPreprocessConfig processPreprocessConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfNodeConfigException(String message,
                                 Throwable cause,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessPreprocessConfig processPreprocessConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfNodeConfigException(Throwable cause,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessPreprocessConfig processPreprocessConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

    public SfNodeConfigException(String message,
                                 Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessPreprocessConfig processPreprocessConfig) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfig = processConfig;
        this.processPreprocessConfig = processPreprocessConfig;
    }

}
