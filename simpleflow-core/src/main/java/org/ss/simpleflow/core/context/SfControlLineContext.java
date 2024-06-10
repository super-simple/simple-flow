package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;

import java.io.Serializable;

public interface SfControlLineContext<NODE_ID, LINE_ID,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>> extends Serializable {

    LINE_CONFIG getLineConfig();

    void putVariable(String key, Object value);

    Object getVariable(String key);
}
