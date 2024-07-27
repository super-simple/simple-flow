package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.io.Serializable;
import java.util.List;

public interface SfProcessConfigGraph<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>> extends Serializable {

    PCI getId();

    List<NC> getNodeConfigList();

    List<EC> getEdgeConfigList();
}
