package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.constant.SfBuiltInEventCodeConstant;
import org.ss.simpleflow.core.constant.SfEventTypeConstant;
import org.ss.simpleflow.core.constant.SfNodeTypeConstant;

import java.util.Map;
import java.util.Set;

public class SfAbstractNodeConfig<NI, PCI> implements SfNodeConfig<NI, PCI> {
    protected NI id;
    protected String nodeType;
    protected String eventCode;
    protected String eventType;
    protected long maxLoopCount = 0L;
    protected boolean resultNode = false;
    protected Map<String, SfNodeParameter> parameterMap;
    protected Map<String, SfNodeResult> resultMap;
    protected Set<String> enumGatewayEnumSet;
    protected PCI processId;

    @Override
    public NI getId() {
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
    public PCI getProcessId() {
        return processId;
    }

    public void setId(NI id) {
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

    public void setProcessId(PCI processId) {
        this.processId = processId;
    }

    public boolean isStartNode() {
        return SfBuiltInEventCodeConstant.isStart(eventCode) && SfEventTypeConstant.isCatch(eventType);
    }

    public boolean isSubProcessNode() {
        return SfNodeTypeConstant.isProcess(nodeType);
    }

    public boolean isLegalEventType() {
        return SfEventTypeConstant.isLegal(eventType);
    }

}
