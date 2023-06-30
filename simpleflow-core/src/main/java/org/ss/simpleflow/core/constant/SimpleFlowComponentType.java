package org.ss.simpleflow.core.constant;

public enum SimpleFlowComponentType {
    NODE(SimpleFlowNodeTypeConstant.NODE),

    EVENT(SimpleFlowNodeTypeConstant.EVENT),
    GATEWAY(SimpleFlowNodeTypeConstant.GATEWAY),

    PROCESS(SimpleFlowNodeTypeConstant.PROCESS),

    LINE(SimpleFlowLineTypeConstant.LINE);

    private final String typeName;

    SimpleFlowComponentType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
