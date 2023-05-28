package org.ss.simpleflow.core.component;

import org.ss.simpleflow.core.SimpleFlowContext;

public interface SimpleFlowComponent {
    String getId();

    String getCode();

    String getName();

    String getDescription();

    SimpleFlowComponentConfig getConfig();

    SimpleFlowContext getContext();
}
