package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowExecutionIdGenerator;
import org.ss.simpleflow.core.SimpleFlowProcessEngine;
import org.ss.simpleflow.core.SimpleFlowValidateAndPreprocess;
import org.ss.simpleflow.core.SimpleFlowWorkDispatcher;
import org.ss.simpleflow.core.constant.SimpleFlowBuiltInEventCodeConstant;
import org.ss.simpleflow.core.constant.SimpleFlowNodeTypeConstant;
import org.ss.simpleflow.core.impl.exceptional.*;
import org.ss.simpleflow.core.impl.factory.SimpleFlowEventFactory;
import org.ss.simpleflow.core.impl.factory.SimpleFlowLineFactory;
import org.ss.simpleflow.core.impl.factory.SimpleFlowNodeFactory;
import org.ss.simpleflow.core.line.SimpleFlowAbstractLineConfig;
import org.ss.simpleflow.core.node.SimpleFlowAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class SimpleFlowProcessEngineImpl implements SimpleFlowProcessEngine {

    private final SimpleFlowValidateAndPreprocess validateAndPreprocess;

    private final SimpleFlowEventFactory eventFactory;

    private final SimpleFlowNodeFactory nodeFactory;

    private final SimpleFlowLineFactory lineFactory;

    private final SimpleFlowWorkDispatcher workDispatcher;

    private final SimpleFlowExecutionIdGenerator executionIdGenerator;

    SimpleFlowProcessEngineImpl(SimpleFlowValidateAndPreprocess validateAndPreprocess, SimpleFlowEventFactory eventFactory,
                                SimpleFlowNodeFactory nodeFactory,
                                SimpleFlowLineFactory lineFactory,
                                SimpleFlowWorkDispatcher workDispatcher,
                                SimpleFlowExecutionIdGenerator executionIdGenerator) {
        if (validateAndPreprocess == null) {
            throw new IllegalArgumentException();
        }
        if (eventFactory == null) {
            throw new IllegalArgumentException();
        }
        if (nodeFactory == null) {
            throw new IllegalArgumentException();
        }
        if (lineFactory == null) {
            throw new IllegalArgumentException();
        }
        if (workDispatcher == null) {
            throw new IllegalArgumentException();
        }
        if (executionIdGenerator == null) {
            throw new IllegalArgumentException();
        }
        this.validateAndPreprocess = validateAndPreprocess;
        this.eventFactory = eventFactory;
        this.nodeFactory = nodeFactory;
        this.lineFactory = lineFactory;
        this.workDispatcher = workDispatcher;
        this.executionIdGenerator = executionIdGenerator;
    }

    @Override
    public String runProcess(SimpleFlowProcessConfig processConfig) {

        validateAndPreprocess.validateAndPreprocess(processConfig);

        Set<? extends SimpleFlowAbstractNodeConfig> nodeConfigSet = processConfig.getNodeConfigSet();
        SimpleFlowAbstractNodeConfig startNodeConfig = null;
        for (SimpleFlowAbstractNodeConfig nodeConfig : nodeConfigSet) {
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
        Map<String, SimpleFlowAbstractNodeConfig> nodeConfigMap = new HashMap<>();

        for (SimpleFlowAbstractNodeConfig nodeConfig : nodeConfigSet) {
            nodeConfigMap.put(nodeConfig.getId(), nodeConfig);
        }

        Set<? extends SimpleFlowAbstractLineConfig> lineConfigSet = processConfig.getLineConfigSet();

        Map<String, SimpleFlowAbstractLineConfig> lineConfigFromMap = new HashMap<>();
        for (SimpleFlowAbstractLineConfig lineConfig : lineConfigSet) {
            lineConfigFromMap.put(lineConfig.getFromNodeId(), lineConfig);
        }

        runNode(processConfig, startNodeConfig, nodeConfigMap, lineConfigFromMap, workDispatcher.getWorkDispatcher());

        return executionIdGenerator.generateProcessExecutionId(processConfig, processConfig.getId(), processConfig.getCode());
    }

    private void runNode(SimpleFlowProcessConfig processConfig, SimpleFlowAbstractNodeConfig nodeConfig, Map<String, SimpleFlowAbstractNodeConfig> nodeConfigMap, Map<String, SimpleFlowAbstractLineConfig> lineConfigFromMap, ExecutorService executorService) {
        CompletableFuture<SimpleFlowAbstractLineConfig> runNodeCompletableFuture = CompletableFuture.supplyAsync(() -> {
            String processId = processConfig.getId();
            String nodeId = nodeConfig.getId();
            String nodeCode = processConfig.getCode();
            String nodeType = nodeConfig.getNodeType();
            if (SimpleFlowNodeTypeConstant.NODE.equals(nodeType)) {
                SimpleFlowAbstractNode node = nodeFactory.getNode(processId, nodeId, nodeCode, nodeConfig);
                node.setId(nodeId);
                node.setCode(nodeCode);
                node.setName(nodeConfig.getName());
                node.setDescription(nodeConfig.getDescription());
                node.setConfig(nodeConfig);
                try {
                    node.runNode();
                } catch (Exception e) {
                    throw new SimpleFlowRunNodeException(e);
                }
            } else if (SimpleFlowNodeTypeConstant.EVENT.equals(nodeType)) {
                SimpleFlowAbstractEvent event = eventFactory.getEvent(processId, nodeId, nodeCode, nodeConfig);
                event.setId(nodeId);
                event.setCode(nodeCode);
                event.setName(nodeConfig.getName());
                event.setDescription(nodeConfig.getDescription());
                event.setConfig(nodeConfig);
                try {
                    event.runEvent();
                } catch (Exception e) {
                    throw new SimpleFlowRunEventException(e);
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

    private void runLine(SimpleFlowProcessConfig processConfig, SimpleFlowAbstractLineConfig lineConfig, Map<String, SimpleFlowAbstractNodeConfig> nodeConfigMap, Map<String, SimpleFlowAbstractLineConfig> lineConfigFromMap, ExecutorService executorService) {
        CompletableFuture<Boolean> runLineCompletableFuture = CompletableFuture.supplyAsync(() -> {
            String lineId = lineConfig.getId();
            String lineCode = lineConfig.getCode();
            SimpleFlowAbstractLine line = lineFactory.getLine(processConfig.getId(), lineId, lineCode, lineConfig);
            line.setId(lineId);
            line.setCode(lineCode);
            line.setName(lineConfig.getName());
            line.setDescription(lineConfig.getDescription());
            line.setConfig(lineConfig);
            try {
                return line.runLine();
            } catch (Exception e) {
                throw new SimpleFlowRunLineException(e);
            }
        }, executorService);
        runLineCompletableFuture.whenCompleteAsync((runLine, throwable) -> {
            if (runLine) {
                String toNodeId = lineConfig.getToNodeId();
                SimpleFlowAbstractNodeConfig toNodeConfig = nodeConfigMap.get(toNodeId);
                runNode(processConfig, toNodeConfig, nodeConfigMap, lineConfigFromMap, executorService);
            }
        }, executorService);
    }

}
