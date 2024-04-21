package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.node.SfNodeConfig;

public class SfNodeConfigException extends RuntimeException {
    private final SfNodeConfigExceptionCode exceptionCode;
    private final SfNodeConfig nodeConfig;

    public SfNodeConfigException(SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig) {
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
    }

    public SfNodeConfigException(String message,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
    }

    public SfNodeConfigException(String message, Throwable cause,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
    }

    public SfNodeConfigException(Throwable cause,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
    }

    public SfNodeConfigException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
    }

    @Override
    public String getMessage() {
        String prefix = "node id[" + nodeConfig.getId() + "] " + exceptionCode.name();
        String message = super.getMessage();
        if (message == null) {
            return prefix;
        } else {
            return prefix + ", " + message;
        }
    }

}
