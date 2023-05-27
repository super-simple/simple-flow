package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class SimpleFlowProcessEngineImpl implements SimpleFlowProcessEngine {


    private SimpleFlowEventFactory eventFactory;

    private SimpleFlowNodeFactory nodeFactory;

    private SimpleFlowLineFactory lineFactory;

    private SimpleFlowWorkDispatcher simpleFlowWorkDispatcher;

    private SimpleFlowExecutionIdGenerator executionIdGenerator;

    public SimpleFlowProcessEngineImpl(SimpleFlowEventFactory eventFactory, SimpleFlowNodeFactory nodeFactory, SimpleFlowLineFactory lineFactory, SimpleFlowWorkDispatcher simpleFlowWorkDispatcher, SimpleFlowExecutionIdGenerator executionIdGenerator) {
        this.eventFactory = eventFactory;
        this.nodeFactory = nodeFactory;
        this.lineFactory = lineFactory;
        this.simpleFlowWorkDispatcher = simpleFlowWorkDispatcher;
        this.executionIdGenerator = executionIdGenerator;
    }

    @Override
    public String runProcess(SimpleFlowProcessConfig processConfig) {
        Set<SimpleFlowNodeConfig> nodeConfigSet = processConfig.getNodeConfigSet();
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

        Set<SimpleFlowLineConfig> lineConfigSet = processConfig.getLineConfigSet();

        Map<String, SimpleFlowLineConfig> lineConfigFromMap = new HashMap<>();
        for (SimpleFlowLineConfig lineConfig : lineConfigSet) {
            lineConfigFromMap.put(lineConfig.getFromNodeId(), lineConfig);
        }

        runNode(processConfig, startNodeConfig, nodeConfigMap, lineConfigFromMap);

        return executionIdGenerator.generateExecutionId(processConfig);
    }

    private void runNode(SimpleFlowProcessConfig processConfig, SimpleFlowNodeConfig nodeConfig, Map<String, SimpleFlowNodeConfig> nodeConfigMap, Map<String, SimpleFlowLineConfig> lineConfigFromMap) {
        CompletableFuture<SimpleFlowLineConfig> runNodeCompletableFuture = CompletableFuture.supplyAsync(new Supplier<SimpleFlowLineConfig>() {
            @Override
            public SimpleFlowLineConfig get() {
                String processId = processConfig.getId();
                String nodeId = nodeConfig.getId();
                String nodeType = nodeConfig.getNodeType();
                if (SimpleFlowNodeTypeConstant.NODE.equals(nodeType)) {
                    SimpleFlowEvent event = eventFactory.getEvent(processId, nodeId);
                    try {
                        event.runEvent();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if (SimpleFlowNodeTypeConstant.EVENT.equals(nodeType)) {
                    SimpleFlowNode node = nodeFactory.getNode(processId, nodeId);
                    try {
                        node.runNode();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    throw new SimpleFlowProcessConfigException(SimpleFlowConfigExceptionCode.ERROR_NODE_TYPE);
                }
                return lineConfigFromMap.get(nodeId);
            }
        }, simpleFlowWorkDispatcher.getWorkDispatcher());

        runNodeCompletableFuture.whenComplete(new BiConsumer<SimpleFlowLineConfig, Throwable>() {
            @Override
            public void accept(SimpleFlowLineConfig lineConfig, Throwable throwable) {
                if (lineConfig != null) {
                    runLine(processConfig, lineConfig, nodeConfigMap, lineConfigFromMap);
                }
            }
        });

    }

    private void runLine(SimpleFlowProcessConfig processConfig, SimpleFlowLineConfig lineConfig, Map<String, SimpleFlowNodeConfig> nodeConfigMap, Map<String, SimpleFlowLineConfig> lineConfigFromMap) {
        CompletableFuture<Boolean> runLineCompletableFuture = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                String lineId = lineConfig.getId();
                SimpleFlowLine line = lineFactory.getLine(processConfig.getId(), lineId);
                try {
                    return line.runLine();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, simpleFlowWorkDispatcher.getWorkDispatcher());
        runLineCompletableFuture.whenComplete(new BiConsumer<Boolean, Throwable>() {
            @Override
            public void accept(Boolean runLine, Throwable throwable) {
                if (runLine) {
                    String toNodeId = lineConfig.getToNodeId();
                    SimpleFlowNodeConfig toNodeConfig = nodeConfigMap.get(toNodeId);
                    runNode(processConfig, toNodeConfig, nodeConfigMap, lineConfigFromMap);
                }
            }
        });
    }

}
