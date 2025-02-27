package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SfValidationWholeContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    protected Set<PCI> referencedSubProcessConfigIdSet;

    protected Map<PCI, Set<PCI>> subProcessContainProcessConfigIdMap;

    protected SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext;

    protected List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>> subValidationProcessContextList;

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

    public SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> getMainProcessValidationContext() {
        return mainProcessValidationContext;
    }

    public void setMainProcessValidationContext(SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> mainProcessValidationContext) {
        this.mainProcessValidationContext = mainProcessValidationContext;
    }

    public List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>> getSubValidationProcessContextList() {
        return subValidationProcessContextList;
    }

    public void setSubValidationProcessContextList(List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PC>> subValidationProcessContextList) {
        this.subValidationProcessContextList = subValidationProcessContextList;
    }
}
