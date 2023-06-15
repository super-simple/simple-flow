package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowValidateAndPreprocess;
import org.ss.simpleflow.core.constant.SimpleFlowBuiltInEventCodeConstant;
import org.ss.simpleflow.core.constant.SimpleFlowNodeTypeConstant;
import org.ss.simpleflow.core.impl.exceptional.SimpleFlowConfigExceptionCode;
import org.ss.simpleflow.core.impl.exceptional.SimpleFlowProcessConfigException;
import org.ss.simpleflow.core.line.SimpleFlowAbstractLineConfig;
import org.ss.simpleflow.core.node.SimpleFlowAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;

import java.util.*;

public class SimpleFlowValidateAndPreprocessImpl implements SimpleFlowValidateAndPreprocess {
    @Override
    public void validateAndPreprocess(SimpleFlowProcessConfig processConfig) {
        Set<? extends SimpleFlowAbstractNodeConfig> nodeConfigSet = processConfig.getNodeConfigSet();

        SimpleFlowAbstractNodeConfig startNodeConfig = null;
        //找有没有开始节点
        for (SimpleFlowAbstractNodeConfig nodeConfig : nodeConfigSet) {
            String nodeType = nodeConfig.getNodeType();
            if (SimpleFlowNodeTypeConstant.EVENT.equals(nodeType)) {
                String eventCode = nodeConfig.getEventCode();
                if (SimpleFlowBuiltInEventCodeConstant.START.equals(eventCode)) {
                    if (startNodeConfig == null) {
                        startNodeConfig = nodeConfig;
                    } else {
                        //多个start节点
                        throw new SimpleFlowProcessConfigException(SimpleFlowConfigExceptionCode.NO_START_EVENT);
                    }
                }
            }
        }
        if (startNodeConfig == null) {
            throw new SimpleFlowProcessConfigException(SimpleFlowConfigExceptionCode.NO_START_EVENT);
        }

        //准备数据结构
        Map<String, SimpleFlowAbstractNodeConfig> nodeConfigMap = new HashMap<>();

        for (SimpleFlowAbstractNodeConfig nodeConfig : nodeConfigSet) {
            nodeConfigMap.put(nodeConfig.getId(), nodeConfig);
        }

        Set<? extends SimpleFlowAbstractLineConfig> lineConfigSet = processConfig.getLineConfigSet();

        Map<String, List<SimpleFlowAbstractLineConfig>> lineConfigFromMap = new HashMap<>();
        for (SimpleFlowAbstractLineConfig lineConfig : lineConfigSet) {
            String fromNodeId = lineConfig.getFromNodeId();
            List<SimpleFlowAbstractLineConfig> lineConfigList = lineConfigFromMap.computeIfAbsent(fromNodeId, k -> new ArrayList<>());
            lineConfigList.add(lineConfig);
        }

        //遍历,预处理,并且检验结束节点
        runNode(startNodeConfig, nodeConfigMap, lineConfigFromMap);
    }

    private void runNode(SimpleFlowAbstractNodeConfig fromNode, Map<String, SimpleFlowAbstractNodeConfig> nodeConfigMap, Map<String, List<SimpleFlowAbstractLineConfig>> lineConfigFromMap) {
        String fromNodeId = fromNode.getId();
        List<SimpleFlowAbstractLineConfig> lineConfigList;
        do {
            lineConfigList = lineConfigFromMap.get(fromNodeId);
            int size = lineConfigList.size();
            if (size == 1) {
                SimpleFlowAbstractLineConfig simpleFlowAbstractLineConfig = lineConfigList.get(0);
                String toNodeId = simpleFlowAbstractLineConfig.getToNodeId();
                lineConfigList = lineConfigFromMap.get(toNodeId);
            } else {
                for (SimpleFlowAbstractLineConfig simpleFlowAbstractLineConfig : lineConfigList) {
                    String toNodeId = simpleFlowAbstractLineConfig.getToNodeId();
                    SimpleFlowAbstractNodeConfig toNode = nodeConfigMap.get(toNodeId);
                    runNode(toNode, nodeConfigMap, lineConfigFromMap);
                }
            }
        } while (lineConfigList != null);
    }

}
