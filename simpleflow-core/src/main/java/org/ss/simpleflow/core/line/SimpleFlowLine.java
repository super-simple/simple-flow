package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SimpleFlowComponent;

public interface SimpleFlowLine extends SimpleFlowComponent {

    SimpleFlowLineConfig getConfig();

    Boolean runLine() throws Exception;
}
