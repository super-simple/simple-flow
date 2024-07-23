package org.ss.simpleflow.core.impl.util;

import org.ss.simpleflow.common.CollectionUtils;

import java.util.Deque;
import java.util.List;

public class StackUtils {

    public static <T> void pushToStack(Deque<T> stack, List<? extends T> componentList) {
        if (CollectionUtils.isNotEmpty(componentList)) {
            for (T sfComponentConfig : componentList) {
                stack.push(sfComponentConfig);
            }
        }
    }

}
