package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.component.SfComponent;

import java.util.Map;

public interface SfNode extends SfComponent {

    Map<String, Object> runNode(Map<String, Object> params, SfNodeContext context) throws Exception;
}