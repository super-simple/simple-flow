package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfControlLineContext;

import java.util.Map;

public interface SfControlLine extends SfComponent {

    boolean executeControlLine(Map<String, Object> params, SfControlLineContext lineContext) throws Exception;
}
