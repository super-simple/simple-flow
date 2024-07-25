package org.ss.simpleflow.core.impl.util;

import org.ss.simpleflow.common.CollectionUtils;

import java.util.Collection;
import java.util.Deque;

public class StackUtils {

    public static <T> void pushAllToStack(Deque<T> stack, Collection<? extends T> componentCollection) {
        if (CollectionUtils.isNotEmpty(componentCollection)) {
            for (T sfComponentConfig : componentCollection) {
                stack.push(sfComponentConfig);
            }
        }
    }

}
