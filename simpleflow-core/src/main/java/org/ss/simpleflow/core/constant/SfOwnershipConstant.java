package org.ss.simpleflow.core.constant;

public interface SfOwnershipConstant {

    String OWNERSHIP_ITERATOR = SfIteratorConstant.ITERATOR;
    String OWNERSHIP_MAIN = "MAIN";

    static boolean isMain(String ownership) {
        return OWNERSHIP_MAIN.equalsIgnoreCase(ownership);
    }

    static boolean isIterator(String ownership) {
        return OWNERSHIP_ITERATOR.equalsIgnoreCase(ownership);
    }
}
