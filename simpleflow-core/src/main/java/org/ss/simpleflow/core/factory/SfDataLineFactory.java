package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfControlLineContext;
import org.ss.simpleflow.core.line.SfDataLine;
import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfDataLineFactory {

    SfDataLine createDataLine(SfLineConfig dataLineConfig,
                              SfProcessConfigGraph processConfigGraph,
                              SfControlLineContext lineContext);

}
