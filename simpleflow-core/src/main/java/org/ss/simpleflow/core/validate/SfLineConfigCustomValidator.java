package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.line.SfLineConfig;

public interface SfLineConfigCustomValidator<NODE_ID, LINE_ID, LINE_CONFIG extends SfLineConfig<LINE_ID, NODE_ID>> {
    void validateSingleLineConfig(LINE_CONFIG lineConfig);

}
