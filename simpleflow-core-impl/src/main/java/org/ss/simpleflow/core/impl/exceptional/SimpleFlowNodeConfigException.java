package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public class SimpleFlowNodeConfigException extends RuntimeException {
    private final SimpleFlowNodeConfigExceptionCode exceptionCode;
    private final SimpleFlowNodeConfig nodeConfig;

    public SimpleFlowNodeConfigException(SimpleFlowNodeConfigExceptionCode exceptionCode,
                                         SimpleFlowNodeConfig nodeConfig) {
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
    }

    public SimpleFlowNodeConfigException(String message,
                                         SimpleFlowNodeConfigExceptionCode exceptionCode,
                                         SimpleFlowNodeConfig nodeConfig) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
    }

    public SimpleFlowNodeConfigException(String message, Throwable cause,
                                         SimpleFlowNodeConfigExceptionCode exceptionCode,
                                         SimpleFlowNodeConfig nodeConfig) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
    }

    public SimpleFlowNodeConfigException(Throwable cause,
                                         SimpleFlowNodeConfigExceptionCode exceptionCode,
                                         SimpleFlowNodeConfig nodeConfig) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
    }

    public SimpleFlowNodeConfigException(String message, Throwable cause,
                                         boolean enableSuppression, boolean writableStackTrace,
                                         SimpleFlowNodeConfigExceptionCode exceptionCode,
                                         SimpleFlowNodeConfig nodeConfig) {
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
