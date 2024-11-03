package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.constant.SfBuiltInEventCodeConstant;
import org.ss.simpleflow.core.constant.SfEventTypeConstant;
import org.ss.simpleflow.core.constant.SfNodeTypeConstant;

import java.util.Set;

public class SfAbstractNodeConfig<NI, PCI> implements SfNodeConfig<NI, PCI> {
    protected NI id;
    protected String nodeType;
    protected String eventCode;
    protected String eventType;
    protected long maxExecuteCount = 1L;
    protected boolean resultNode = false;
    protected SfNodeParameter[] parameter;
    protected SfNodeResult[] result;
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
    public long getMaxExecuteCount() {
        return maxExecuteCount;
    }

    @Override
    public boolean isResultNode() {
        return resultNode;
    }

    @Override
    public SfNodeParameter[] getParameter() {
        return parameter;
    }

    @Override
    public SfNodeResult[] getResult() {
        return result;
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

    public void setMaxExecuteCount(long maxExecuteCount) {
        this.maxExecuteCount = maxExecuteCount;
    }

    public void setResultNode(boolean resultNode) {
        this.resultNode = resultNode;
    }

    public void setParameter(SfNodeParameter[] parameter) {
        this.parameter = parameter;
    }

    public void setResult(SfNodeResult[] result) {
        this.result = result;
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
