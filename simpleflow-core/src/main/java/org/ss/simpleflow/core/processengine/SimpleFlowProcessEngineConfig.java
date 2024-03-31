package org.ss.simpleflow.core.processengine;

public interface SimpleFlowProcessEngineConfig {

    boolean enableTrim();

    long maxLoopCount();

    String contextSchema();

}
