package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowExecutionIdGenerator;
import org.ss.simpleflow.core.SimpleFlowProcessEngine;
import org.ss.simpleflow.core.SimpleFlowWorkDispatcher;
import org.ss.simpleflow.core.constant.SimpleFlowBuiltInEventCodeConstant;
import org.ss.simpleflow.core.constant.SimpleFlowNodeTypeConstant;
import org.ss.simpleflow.core.impl.exceptional.*;
import org.ss.simpleflow.core.line.SimpleFlowLineConfig;
import org.ss.simpleflow.core.node.SimpleFlowAbstractNode;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;
import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class SimpleFlowProcessEngineImpl implements SimpleFlowProcessEngine {


    private final SimpleFlowEventFactory eventFactory;

    private final SimpleFlowNodeFactory nodeFactory;

    private final SimpleFlowLineFactory lineFactory;

    private final SimpleFlowWorkDispatcher workDispatcher;

    private final SimpleFlowExecutionIdGenerator executionIdGenerator;

    public SimpleFlowProcessEngineImpl(SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory, SimpleFlowWorkDispatcher workDispatcher, SimpleFlowExecutionIdGenerator executionIdGenerator) {
        this.eventFactory = eventFactory;
        this.nodeFactory = nodeFactory;
        this.lineFactory = lineFactory;
        this.workDispatcher = workDispatcher;
        this.executionIdGenerator = executionIdGenerator;
    }

    @Override
    public String runProcess(SimpleFlowProcessConfig processConfig) {
        Set<? extends SimpleFlowNodeConfig> nodeConfigSet = processConfig.getNodeConfigSet();
        SimpleFlowNodeConfig startNodeConfig = null;
        for (SimpleFlowNodeConfig nodeConfig : nodeConfigSet) {
            String nodeType = nodeConfig.getNodeType();
            if (SimpleFlowNodeTypeConstant.EVENT.equals(nodeType)) {
                String eventCode = nodeConfig.getEventCode();
                if (SimpleFlowBuiltInEventCodeConstant.START.equals(eventCode)) {
                    startNodeConfig = nodeConfig;
                    break;
                }
            }
        }
        if (startNodeConfig == null) {
            throw new SimpleFlowProcessConfigException(SimpleFlowConfigExceptionCode.NO_START_EVENT);
        }
        Map<String, SimpleFlowNodeConfig> nodeConfigMap = new HashMap<>();

        for (SimpleFlowNodeConfig nodeConfig : nodeConfigSet) {
            nodeConfigMap.put(nodeConfig.getId(), nodeConfig);
        }

        Set<? extends SimpleFlowLineConfig> lineConfigSet = processConfig.getLineConfigSet();

        Map<String, SimpleFlowLineConfig> lineConfigFromMap = new HashMap<>();
        for (SimpleFlowLineConfig lineConfig : lineConfigSet) {
            lineConfigFromMap.put(lineConfig.getFromNodeId(), lineConfig);
        }

        runNode(processConfig, startNodeConfig, nodeConfigMap, lineConfigFromMap, workDispatcher.getWorkDispatcher());

        return executionIdGenerator.generateProcessExecutionId(processConfig, processConfig.getId(), processConfig.getCode());
    }

    private void runNode(SimpleFlowProcessConfig processConfig, SimpleFlowNodeConfig nodeConfig, Map<String, SimpleFlowNodeConfig> nodeConfigMap, Map<String, SimpleFlowLineConfig> lineConfigFromMap, ExecutorService executorService) {
        CompletableFuture<SimpleFlowLineConfig> runNodeCompletableFuture = CompletableFuture.supplyAsync(() -> {
            String processId = processConfig.getId();
            String nodeId = nodeConfig.getId();
            String nodeCode = processConfig.getCode();
            String nodeType = nodeConfig.getNodeType();
            if (SimpleFlowNodeTypeConstant.NODE.equals(nodeType)) {
                SimpleFlowAbstractEvent event = eventFactory.getEvent(processId, nodeId, nodeCode, nodeConfig);
                try {
                    event.runEvent();
                } catch (Exception e) {
                    throw new SimpleFlowRunEventException(e);
                }
            } else if (SimpleFlowNodeTypeConstant.EVENT.equals(nodeType)) {
                SimpleFlowAbstractNode node = nodeFactory.getNode(processId, nodeId, nodeCode, nodeConfig);
                try {
                    node.runNode();
                } catch (Exception e) {
                    throw new SimpleFlowRunNodeException(e);
                }
            } else {
                throw new SimpleFlowProcessConfigException(SimpleFlowConfigExceptionCode.ERROR_NODE_TYPE);
            }
            return lineConfigFromMap.get(nodeId);
        }, executorService);

        runNodeCompletableFuture.whenCompleteAsync((lineConfig, throwable) -> {
            if (lineConfig != null) {
                runLine(processConfig, lineConfig, nodeConfigMap, lineConfigFromMap, executorService);
            }
        }, executorService);

    }

    private void runLine(SimpleFlowProcessConfig processConfig, SimpleFlowLineConfig lineConfig, Map<String, SimpleFlowNodeConfig> nodeConfigMap, Map<String, SimpleFlowLineConfig> lineConfigFromMap, ExecutorService executorService) {
        CompletableFuture<Boolean> runLineCompletableFuture = CompletableFuture.supplyAsync(() -> {
            String lineId = lineConfig.getId();
            SimpleFlowAbstractLine line = lineFactory.getLine(processConfig.getId(), lineId, lineConfig.getCode(), lineConfig);
            try {
                return line.runLine();
            } catch (Exception e) {
                throw new SimpleFlowRunLineException(e);
            }
        }, executorService);
        runLineCompletableFuture.whenCompleteAsync((runLine, throwable) -> {
            if (runLine) {
                String toNodeId = lineConfig.getToNodeId();
                SimpleFlowNodeConfig toNodeConfig = nodeConfigMap.get(toNodeId);
                runNode(processConfig, toNodeConfig, nodeConfigMap, lineConfigFromMap, executorService);
            }
        }, executorService);
    }

}
