package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfSubProcessConfig;

public interface SfProcessConfigFactory {

    SfProcessConfig createProcessConfig();

    SfSubProcessConfig createSubProcessConfig();
}
