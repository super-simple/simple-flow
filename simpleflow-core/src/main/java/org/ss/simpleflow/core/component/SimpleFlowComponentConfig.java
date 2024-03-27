package org.ss.simpleflow.core.component;


import java.util.Set;

public interface SimpleFlowComponentConfig {
    String getId();

    String getCode();

    Set<String> getTag();

    String getName();

    String getDescription();
}
