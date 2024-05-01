package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.line.SfLineConfig;

import java.io.Serializable;

public interface SfLineContext extends Serializable {

    SfLineConfig getConfig();

    void putVariable(String key, Object value);

    Object getVariable(String key);
}
