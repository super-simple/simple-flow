package org.ss.simpleflow.core.event;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;

import java.util.Map;

public interface SfEvent extends SfComponent {

    Map<String, Object> runEvent(Map<String, Object> params,
                                 SfNodeContext context) throws Exception;
}
