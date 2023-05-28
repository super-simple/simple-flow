package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.component.SimpleFlowComponent;

public interface SimpleFlowNode extends SimpleFlowComponent {

    SimpleFlowNodeConfig getConfig();

    void runNode() throws Exception;
}
