package org.ss.simpleflow.core;

public interface SimpleFlowComponent {
    String getId();

    SimpleFlowComponentConfig getConfig();

    SimpleFlowContext getContext();
}
