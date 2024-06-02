package org.ss.simpleflow.core.processconfig;

import java.util.List;

public interface SfProcessConfig extends SfProcessConfigGraph {
    List<SfProcessConfigGraph> getSubProcessConfigList();

}
