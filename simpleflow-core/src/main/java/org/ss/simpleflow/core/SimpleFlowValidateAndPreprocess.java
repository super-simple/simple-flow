package org.ss.simpleflow.core;

/**
 * 校验
 */
public interface SimpleFlowValidateAndPreprocess {

    void validateNodeConfig();

    void validateLineConfig();

    void validateGraphAndBusiness();

}
