package org.ss.simpleflow.core.impl.exceptional;

public enum SfProcessConfigExceptionCode {
    ID_REPEAT,
    EXIST_ORPHAN_COMPONENT,
    NO_NODE,
    NO_SUB_PROCESS,
    CIRCULAR_REFERENCE,
    NO_START_EVENT,
    NO_PROCESS_ID
}
