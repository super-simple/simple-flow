package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfProcessConfigFactory {

    SfProcessConfig createProcessConfig();

    SfProcessConfigGraph createSubProcessConfig();
}
