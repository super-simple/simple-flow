package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;

import java.util.Map;

public interface SfNode extends SfComponent {

    Map<String, Object> executeNode(Map<String, Object> params, SfNodeContext context) throws Exception;
}
