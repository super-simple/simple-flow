package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.Map;
import java.util.Set;

public abstract class SfValidationWholeContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    protected Set<PCI> referencedSubProcessConfigIdSet;

    protected Map<PCI, Set<PCI>> subProcessContainProcessConfigIdMap;

    protected SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainValidationProcessContext;

    protected SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] subValidationProcessContextArray;

    public Set<PCI> getReferencedSubProcessConfigIdSet() {
        return referencedSubProcessConfigIdSet;
    }

    public void setReferencedSubProcessConfigIdSet(Set<PCI> referencedSubProcessConfigIdSet) {
        this.referencedSubProcessConfigIdSet = referencedSubProcessConfigIdSet;
    }

    public Map<PCI, Set<PCI>> getSubProcessContainProcessConfigIdMap() {
        return subProcessContainProcessConfigIdMap;
    }

    public void setSubProcessContainProcessConfigIdMap(Map<PCI, Set<PCI>> subProcessContainProcessConfigIdMap) {
        this.subProcessContainProcessConfigIdMap = subProcessContainProcessConfigIdMap;
    }

    public SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> getMainValidationProcessContext() {
        return mainValidationProcessContext;
    }

    public void setMainValidationProcessContext(SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainValidationProcessContext) {
        this.mainValidationProcessContext = mainValidationProcessContext;
    }

    public SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] getSubValidationProcessContextArray() {
        return subValidationProcessContextArray;
    }

    public void setSubValidationProcessContextArray(SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>[] subValidationProcessContextArray) {
        this.subValidationProcessContextArray = subValidationProcessContextArray;
    }
}
