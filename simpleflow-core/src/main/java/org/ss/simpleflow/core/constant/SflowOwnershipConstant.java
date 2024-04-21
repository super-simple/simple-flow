package org.ss.simpleflow.core.constant;

public interface SflowOwnershipConstant {

    String OWNERSHIP_ITERATOR = SfIteratorConstant.ITERATOR;
    String OWNERSHIP_MAIN = "MAIN";

    static boolean isMainOwnership(String ownership) {
        return OWNERSHIP_MAIN.equalsIgnoreCase(ownership);
    }

    static boolean isIteratorOwnership(String ownership) {
        return OWNERSHIP_ITERATOR.equalsIgnoreCase(ownership);
    }
}
