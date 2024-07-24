package org.ss.simpleflow.core.edge;

import org.ss.simpleflow.core.component.SfComponentConfig;

public interface SfEdgeConfig<EDGE_ID, NODE_ID> extends SfComponentConfig {

    EDGE_ID getId();

    NODE_ID getFromNodeId();

    NODE_ID getToNodeId();

    String getEdgeType();

    String getFromResultKey();

    String getToParameterKey();

}
