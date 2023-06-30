package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SimpleFlowComponent;

import java.util.Map;

public interface SimpleFlowLine extends SimpleFlowComponent {

    boolean runLine(Map<String, Object> params, SimpleFlowLineContext lineContext) throws Exception;
}
