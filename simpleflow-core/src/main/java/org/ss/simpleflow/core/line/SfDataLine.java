package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SfComponent;

public interface SfDataLine extends SfComponent {

    Object executeDateLine(String key, String object, SfLineConfig lineConfig) throws Exception;
}
