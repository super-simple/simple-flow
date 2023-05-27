package org.ss.simpleflow.core.impl;

public class SimpleFlowProcessConfigException extends RuntimeException {

    private SimpleFlowConfigExceptionCode configExceptionCode;

    public SimpleFlowProcessConfigException(SimpleFlowConfigExceptionCode configExceptionCode) {
        super(configExceptionCode.toString());
        this.configExceptionCode = configExceptionCode;
    }

}
