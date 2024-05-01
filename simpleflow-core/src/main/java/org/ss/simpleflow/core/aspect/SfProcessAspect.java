package org.ss.simpleflow.core.aspect;

public interface SfProcessAspect {

    void before();

    void afterSuccess();

    void afterThrowing();

    void afterAnyway();

}
