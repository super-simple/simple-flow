package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.util.List;

public abstract class SfAbstractProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>> implements SfProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH> {

    protected PROCESS_CONFIG_ID id;
    protected List<NODE_CONFIG> nodeConfigList;
    protected List<EDGE_CONFIG> edgeConfigList;
    protected List<PROCESS_CONFIG_GRAPH> subProcessConfigList;


    public void setId(PROCESS_CONFIG_ID id) {
        this.id = id;
    }

    @Override
    public PROCESS_CONFIG_ID getId() {
        return id;
    }

    public void setNodeConfigList(List<NODE_CONFIG> nodeConfigList) {
        this.nodeConfigList = nodeConfigList;
    }

    @Override
    public List<NODE_CONFIG> getNodeConfigList() {
        return nodeConfigList;
    }

    public void setEdgeConfigList(List<EDGE_CONFIG> edgeConfigList) {
        this.edgeConfigList = edgeConfigList;
    }

    @Override
    public List<EDGE_CONFIG> getEdgeConfigList() {
        return edgeConfigList;
    }

    public void setSubProcessConfigList(List<PROCESS_CONFIG_GRAPH> subProcessConfigList) {
        this.subProcessConfigList = subProcessConfigList;
    }

    @Override
    public List<PROCESS_CONFIG_GRAPH> getSubProcessConfigList() {
        return subProcessConfigList;
    }

}
