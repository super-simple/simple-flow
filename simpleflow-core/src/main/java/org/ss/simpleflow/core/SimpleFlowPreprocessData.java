package org.ss.simpleflow.core;

import org.ss.simpleflow.core.line.SimpleFlowAbstractLineConfig;
import org.ss.simpleflow.core.node.SimpleFlowAbstractNodeConfig;

import java.util.List;
import java.util.Map;

public class SimpleFlowPreprocessData {
    private SimpleFlowAbstractNodeConfig startNodeConfig;

    private Map<String, SimpleFlowPreprocessNodeData> preprocessNodeDataMap;

    private Map<String, ? extends SimpleFlowAbstractNodeConfig> nodeConfigMap;

    private Map<String, ? extends List<? extends SimpleFlowAbstractLineConfig>> lineConfigFromMap;

    public SimpleFlowAbstractNodeConfig getStartNodeConfig() {
        return startNodeConfig;
    }

    public void setStartNodeConfig(SimpleFlowAbstractNodeConfig startNodeConfig) {
        this.startNodeConfig = startNodeConfig;
    }

    public Map<String, SimpleFlowPreprocessNodeData> getPreprocessNodeDataMap() {
        return preprocessNodeDataMap;
    }

    public void setPreprocessNodeDataMap(Map<String, SimpleFlowPreprocessNodeData> preprocessNodeDataMap) {
        this.preprocessNodeDataMap = preprocessNodeDataMap;
    }

    public Map<String, ? extends SimpleFlowAbstractNodeConfig> getNodeConfigMap() {
        return nodeConfigMap;
    }

    public void setNodeConfigMap(Map<String, ? extends SimpleFlowAbstractNodeConfig> nodeConfigMap) {
        this.nodeConfigMap = nodeConfigMap;
    }

    public Map<String, ? extends List<? extends SimpleFlowAbstractLineConfig>> getLineConfigFromMap() {
        return lineConfigFromMap;
    }

    public void setLineConfigFromMap(Map<String, ? extends List<? extends SimpleFlowAbstractLineConfig>> lineConfigFromMap) {
        this.lineConfigFromMap = lineConfigFromMap;
    }
}
