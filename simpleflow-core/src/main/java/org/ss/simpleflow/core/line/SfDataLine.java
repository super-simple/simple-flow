package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SfComponent;

public interface SfDataLine<NODE_ID, LINE_ID,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>> extends SfComponent {

    Object executeDataLine(String key, String object, LINE_CONFIG lineConfig) throws Exception;
}
