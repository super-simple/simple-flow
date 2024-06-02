package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfControlLineContext;
import org.ss.simpleflow.core.line.SfControlLine;
import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfControlLineFactory {

    SfControlLine createControlLine(SfProcessConfigGraph processConfigGraph,
                                    SfLineConfig gatewayConfig,
                                    SfControlLineContext lineContext);

}
