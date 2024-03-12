package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.MapUtils;
import org.ss.simpleflow.common.MultiMapUtils;
import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.SimpleFlowPreprocessData;
import org.ss.simpleflow.core.SimpleFlowPreprocessNodeData;
import org.ss.simpleflow.core.SimpleFlowValidateAndPreprocess;
import org.ss.simpleflow.core.constant.SimpleFlowBuiltInEventCodeConstant;
import org.ss.simpleflow.core.constant.SimpleFlowNodeTypeConstant;
import org.ss.simpleflow.core.impl.exceptional.SimpleFlowConfigExceptionCode;
import org.ss.simpleflow.core.impl.exceptional.SimpleFlowProcessConfigException;
import org.ss.simpleflow.core.line.SimpleFlowAbstractLineConfig;
import org.ss.simpleflow.core.node.SimpleFlowAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;

import java.util.*;

public class SimpleFlowUniqueValidateAndPreprocess implements SimpleFlowValidateAndPreprocess {

    private final SimpleFlowAbstractNodeConfigValidator nodeConfigValidator;

    private final SimpleFlowAbstractLineConfigValidator lineConfigValidator;

    SimpleFlowUniqueValidateAndPreprocess(SimpleFlowAbstractNodeConfigValidator nodeConfigValidator,
                                          SimpleFlowAbstractLineConfigValidator lineConfigValidator) {
        if (nodeConfigValidator == null) {
            throw new IllegalArgumentException();
        }
        if (lineConfigValidator == null) {
            throw new IllegalArgumentException();
        }
        this.nodeConfigValidator = nodeConfigValidator;
        this.lineConfigValidator = lineConfigValidator;
    }

    @Override
    public SimpleFlowPreprocessData validateAndPreprocess(SimpleFlowProcessConfig processConfig) {
        SimpleFlowPreprocessData preprocessData = new SimpleFlowPreprocessData();
        Set<? extends SimpleFlowAbstractNodeConfig> nodeConfigSet = processConfig.getNodeConfigSet();
        Set<? extends SimpleFlowAbstractLineConfig> lineConfigSet = processConfig.getLineConfigSet();

        validateIdRepeat(nodeConfigSet);

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
        preprocessData.setStartNodeConfig(startNodeConfig);

        //准备数据结构
        Map<String, ? extends SimpleFlowAbstractNodeConfig> nodeConfigMap = MapUtils.uniqueIndex(nodeConfigSet, SimpleFlowAbstractNodeConfig::getId);
        preprocessData.setNodeConfigMap(nodeConfigMap);

        Map<String, ? extends List<? extends SimpleFlowAbstractLineConfig>> lineConfigFromMap = MultiMapUtils.index(lineConfigSet, SimpleFlowAbstractLineConfig::getFromNodeId);
        preprocessData.setLineConfigFromMap(lineConfigFromMap);

        Map<String, SimpleFlowPreprocessNodeData> preprocessNodeDataMap = new HashMap<>();
        preprocessData.setPreprocessNodeDataMap(preprocessNodeDataMap);
        SimpleFlowPreprocessNodeData preprocessNodeData = new SimpleFlowPreprocessNodeData();
        Set<String> parentSet = new HashSet<>();
        String startNodeId = startNodeConfig.getId();
        parentSet.add(startNodeId);
        preprocessNodeData.setParentSet(parentSet);
        preprocessNodeDataMap.put(startNodeId, preprocessNodeData);

        //遍历,预处理,并且检验结束节点
        runNode(startNodeConfig, startNodeConfig, new HashSet<>(), nodeConfigMap, lineConfigFromMap, preprocessNodeDataMap);
        return preprocessData;
    }

    private static void validateIdRepeat(Set<? extends SimpleFlowAbstractNodeConfig> nodeConfigSet) {
        Set<String> idSet = new HashSet<>();
        for (SimpleFlowAbstractNodeConfig simpleFlowAbstractNodeConfig : nodeConfigSet) {
            String id = simpleFlowAbstractNodeConfig.getId();
            if (StringUtils.isNotEmpty(id)) {
                boolean add = idSet.add(id);
                if (!add) {
                    throw new SimpleFlowProcessConfigException(SimpleFlowConfigExceptionCode.ID_REPEAT);
                }
            } else {
                throw new SimpleFlowProcessConfigException(SimpleFlowConfigExceptionCode.NO_ID);
            }
        }
    }

    private void runNode(SimpleFlowAbstractNodeConfig lastFromNode, SimpleFlowAbstractNodeConfig fromNode,
                         Set<String> lineSet,
                         Map<String, ? extends SimpleFlowAbstractNodeConfig> nodeConfigMap,
                         Map<String, ? extends List<? extends SimpleFlowAbstractLineConfig>> lineConfigFromMap,
                         Map<String, SimpleFlowPreprocessNodeData> preprocessNodeDataMap) {
        String lastFromNodeId = lastFromNode.getId();
        String fromNodeId = fromNode.getId();
        List<? extends SimpleFlowAbstractLineConfig> lineConfigList;
        do {
            lineConfigList = lineConfigFromMap.get(fromNodeId);
            if (CollectionUtils.isNotEmpty(lineConfigList)) {
                int size = lineConfigList.size();

                SimpleFlowPreprocessNodeData lastPreprocessNodeData = preprocessNodeDataMap.get(lastFromNodeId);
                SimpleFlowPreprocessNodeData currentPreprocessNodeData = new SimpleFlowPreprocessNodeData();
                currentPreprocessNodeData.setParentSet(lastPreprocessNodeData.getParentSet());
                preprocessNodeDataMap.put(fromNodeId, currentPreprocessNodeData);

                if (size == 1) {
                    SimpleFlowAbstractLineConfig simpleFlowAbstractLineConfig = lineConfigList.get(0);
                    String toNodeId = simpleFlowAbstractLineConfig.getToNodeId();
                    lineConfigList = lineConfigFromMap.get(toNodeId);
                    lastFromNodeId = fromNodeId;
                    fromNodeId = toNodeId;
                } else {
                    for (SimpleFlowAbstractLineConfig simpleFlowAbstractLineConfig : lineConfigList) {
                        String lineId = simpleFlowAbstractLineConfig.getId();
                        if (lineSet.contains(lineId)) {
                            return;
                        } else {
                            lineSet.add(lineId);
                        }

                        String toNodeId = simpleFlowAbstractLineConfig.getToNodeId();
                        SimpleFlowAbstractNodeConfig toNode = nodeConfigMap.get(toNodeId);
                        runNode(fromNode, toNode, lineSet, nodeConfigMap, lineConfigFromMap, preprocessNodeDataMap);
                    }
                }
            }
        } while (lineConfigList != null && lineConfigList.size() == 1);
    }

}
