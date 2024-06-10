package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfControlLineContext;

import java.util.Map;

public interface SfControlLine<NODE_ID, LINE_ID,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>> extends SfComponent {

    boolean executeControlLine(Map<String, Object> params,
                               SfControlLineContext<NODE_ID, LINE_ID, LINE_CONFIG> lineContext) throws Exception;
}
