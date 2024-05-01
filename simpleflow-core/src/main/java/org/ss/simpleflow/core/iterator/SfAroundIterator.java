package org.ss.simpleflow.core.iterator;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;

import java.util.Map;

public interface SfAroundIterator extends SfComponent {

    Map<String, Object> runGateway(Map<String, Object> params,
                                   SfNodeContext context) throws Exception;
}
