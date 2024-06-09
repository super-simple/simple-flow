package org.ss.simpleflow.core.node;

import java.util.Map;
import java.util.Set;

public class SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID> implements SfNodeConfig<NODE_ID, PROCESS_CONFIG_ID> {
    protected NODE_ID id;
    protected String nodeType;
    protected String eventCode;
    protected String eventType;
    protected long maxLoopCount = 0L;
    protected boolean resultNode = false;
    protected Map<String, SfNodeParameter> parameterMap;
    protected Map<String, SfNodeResult> resultMap;
    protected Set<String> enumGatewayEnumSet;
    protected SfNodeIndexEntry nodeIndexEntry;
    protected PROCESS_CONFIG_ID processId;

    @Override
    public NODE_ID getId() {
        return id;
    }

    @Override
    public String getNodeType() {
        return nodeType;
    }

    @Override
    public String getEventCode() {
        return eventCode;
    }

    @Override
    public String getEventType() {
        return eventType;
    }

    @Override
    public long getMaxLoopCount() {
        return maxLoopCount;
    }

    @Override
    public boolean isResultNode() {
        return resultNode;
    }

    @Override
    public Map<String, SfNodeParameter> getParameterMap() {
        return parameterMap;
    }

    @Override
    public Map<String, SfNodeResult> getResultMap() {
        return resultMap;
    }

    @Override
    public Set<String> getEnumGatewayEnumSet() {
        return enumGatewayEnumSet;
    }

    @Override
    public SfNodeIndexEntry getNodeIndexEntry() {
        return nodeIndexEntry;
    }

    @Override
    public PROCESS_CONFIG_ID getProcessId() {
        return processId;
    }

    public void setId(NODE_ID id) {
        this.id = id;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setMaxLoopCount(long maxLoopCount) {
        this.maxLoopCount = maxLoopCount;
    }

    public void setResultNode(boolean resultNode) {
        this.resultNode = resultNode;
    }

    public void setParameterMap(Map<String, SfNodeParameter> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public void setResultMap(Map<String, SfNodeResult> resultMap) {
        this.resultMap = resultMap;
    }

    public void setEnumGatewayEnumSet(Set<String> enumGatewayEnumSet) {
        this.enumGatewayEnumSet = enumGatewayEnumSet;
    }

    public void setNodeIndexEntry(SfNodeIndexEntry nodeIndexEntry) {
        this.nodeIndexEntry = nodeIndexEntry;
    }

    public void setProcessId(PROCESS_CONFIG_ID processId) {
        this.processId = processId;
    }
}
