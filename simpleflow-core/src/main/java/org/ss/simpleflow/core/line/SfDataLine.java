package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfLineContext;

public interface SfDataLine<NODE_ID, LINE_ID, LINE_EXECUTION_ID,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>> extends SfComponent {

    Object executeDataLine(Object source,
                           SfLineContext<NODE_ID, LINE_ID, LINE_EXECUTION_ID, LINE_CONFIG> lineContext) throws Exception;
}
