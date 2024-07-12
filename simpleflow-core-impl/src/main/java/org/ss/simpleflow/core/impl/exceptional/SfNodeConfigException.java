package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.node.SfNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public class SfNodeConfigException extends RuntimeException {
    private final SfNodeConfigExceptionCode exceptionCode;
    private final SfNodeConfig nodeConfig;
    private final SfProcessConfigGraph processConfigGraph;

    public SfNodeConfigException(SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfigGraph processConfigGraph) {
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfigGraph = processConfigGraph;
    }

    public SfNodeConfigException(String message,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfigGraph processConfigGraph) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfigGraph = processConfigGraph;
    }

    public SfNodeConfigException(String message,
                                 Throwable cause,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfigGraph processConfigGraph) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfigGraph = processConfigGraph;
    }

    public SfNodeConfigException(Throwable cause,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfigGraph processConfigGraph) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfigGraph = processConfigGraph;
    }

    public SfNodeConfigException(String message,
                                 Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace,
                                 SfNodeConfigExceptionCode exceptionCode,
                                 SfNodeConfig nodeConfig,
                                 SfProcessConfigGraph processConfigGraph) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.nodeConfig = nodeConfig;
        this.processConfigGraph = processConfigGraph;
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
