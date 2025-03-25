package org.ss.simpleflow.core.edge;

import org.ss.simpleflow.core.component.SfComponentConfig;

public interface SfEdgeConfig<EI, NI> extends SfComponentConfig {

    EI getId();

    NI getFromNodeId();

    NI getToNodeId();

    String getEdgeType();

    int getExecutePriority();

    int getFromResultIndex();

    int getToParameterIndex();

}
