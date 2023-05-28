package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowWorkDispatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimpleFlowWorkDispatcherImpl implements SimpleFlowWorkDispatcher {

    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 60, TimeUnit.SECONDS, new SynchronousQueue<>());

    @Override
    public ExecutorService getWorkDispatcher() {
        return threadPoolExecutor;
    }

}
