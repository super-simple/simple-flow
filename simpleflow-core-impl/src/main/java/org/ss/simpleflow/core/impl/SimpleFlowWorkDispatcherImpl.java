package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowWorkDispatcher;

import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimpleFlowWorkDispatcherImpl implements SimpleFlowWorkDispatcher {

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 60, TimeUnit.SECONDS, new SynchronousQueue<>());

    @Override
    public Executor getWorkDispatcher() {
        return threadPoolExecutor;
    }
}
