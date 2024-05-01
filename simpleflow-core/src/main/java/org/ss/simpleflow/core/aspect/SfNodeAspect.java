package org.ss.simpleflow.core.aspect;

public interface SfNodeAspect {

    void before();

    void afterSuccess();

    void afterThrowing();

    void afterAnyway();

}
