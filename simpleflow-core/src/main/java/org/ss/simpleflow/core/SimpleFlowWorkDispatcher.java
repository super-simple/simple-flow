package org.ss.simpleflow.core;

import java.util.concurrent.ExecutorService;

public interface SimpleFlowWorkDispatcher {

    ExecutorService getWorkDispatcher();

}
