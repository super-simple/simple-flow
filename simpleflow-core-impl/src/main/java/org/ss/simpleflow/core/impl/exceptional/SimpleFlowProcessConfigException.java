package org.ss.simpleflow.core.impl.exceptional;

public class SimpleFlowProcessConfigException extends RuntimeException {

    private final SimpleFlowConfigExceptionCode configExceptionCode;

    public SimpleFlowProcessConfigException(SimpleFlowConfigExceptionCode configExceptionCode) {
        super(configExceptionCode.toString());
        this.configExceptionCode = configExceptionCode;
    }

}
