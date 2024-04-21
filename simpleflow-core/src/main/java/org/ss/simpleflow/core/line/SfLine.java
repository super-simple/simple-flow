package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SfComponent;

import java.util.Map;

public interface SfLine extends SfComponent {

    boolean runLine(Map<String, Object> params, SfLineContext lineContext) throws Exception;
}
