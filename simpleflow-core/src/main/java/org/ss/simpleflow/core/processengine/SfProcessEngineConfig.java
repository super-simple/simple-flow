package org.ss.simpleflow.core.processengine;

public interface SfProcessEngineConfig {

    boolean enableTrim();

    long maxLoopCount();

    boolean throwExceptionWithConfig();
}
