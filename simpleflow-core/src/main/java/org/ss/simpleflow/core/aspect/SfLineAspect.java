package org.ss.simpleflow.core.aspect;

public interface SfLineAspect {

    void before();

    void afterSuccess();

    void afterThrowing();

    void afterAnyway();

}
