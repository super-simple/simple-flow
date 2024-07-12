package org.ss.simpleflow.core.impl.exceptional;

import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public class SfProcessConfigException extends RuntimeException {

    private final SfProcessConfigExceptionCode exceptionCode;
    private final SfProcessConfigGraph processConfigGraph;

    public SfProcessConfigException(SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfigGraph processConfigGraph) {
        this.exceptionCode = exceptionCode;
        this.processConfigGraph = processConfigGraph;
    }

    public SfProcessConfigException(String message,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfigGraph processConfigGraph) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.processConfigGraph = processConfigGraph;
    }

    public SfProcessConfigException(String message,
                                    Throwable cause,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfigGraph processConfigGraph) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.processConfigGraph = processConfigGraph;
    }

    public SfProcessConfigException(Throwable cause,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfigGraph processConfigGraph) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.processConfigGraph = processConfigGraph;
    }

    public SfProcessConfigException(String message,
                                    Throwable cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace,
                                    SfProcessConfigExceptionCode exceptionCode,
                                    SfProcessConfigGraph processConfigGraph) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
        this.processConfigGraph = processConfigGraph;
    }


}
