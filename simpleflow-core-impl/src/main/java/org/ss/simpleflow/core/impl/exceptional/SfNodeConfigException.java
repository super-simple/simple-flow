package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.node.SfNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

public class SfNodeConfigException extends RuntimeException {
    private final SfNodeConfigExceptionCode exceptionCode;
    private final SfNodeConfig nodeConfig;
    private final SfProcessConfig processConfig;
    private final SfProcessEngineConfig processEngineConfig;

    public SfNodeConfigException(SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessEngineConfig processEngineConfig) {
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfig = processConfig;
        this.processEngineConfig = processEngineConfig;
    }

    public SfNodeConfigException(String message,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessEngineConfig processEngineConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfig = processConfig;
        this.processEngineConfig = processEngineConfig;
    }

    public SfNodeConfigException(String message,
                                 Throwable cause,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessEngineConfig processEngineConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfig = processConfig;
        this.processEngineConfig = processEngineConfig;
    }

    public SfNodeConfigException(Throwable cause,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessEngineConfig processEngineConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfig = processConfig;
        this.processEngineConfig = processEngineConfig;
    }

    public SfNodeConfigException(String message,
                                 Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfig processConfig,
                                 SfProcessEngineConfig processEngineConfig) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfig = processConfig;
        this.processEngineConfig = processEngineConfig;
    }

}
