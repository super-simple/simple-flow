package org.ss.simpleflow.core.constant;

public enum SimpleFlowComponentType {
    NODE(SimpleFlowNodeTypeConstant.NODE),

    EVENT(SimpleFlowNodeTypeConstant.EVENT),

    PROCESS(SimpleFlowNodeTypeConstant.PROCESS),

    LINE(SimpleFlowComponentTypeConstant.LINE);

    private final String typeName;

    SimpleFlowComponentType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
