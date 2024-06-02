package org.ss.simpleflow.core.processengine;

import java.io.Serializable;

public interface SfProcessEngineConfig extends Serializable {

    boolean enableTrim();

    long maxLoopCount();

    String developMode();
}
