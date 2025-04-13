package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

public interface SfProcessConfig<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>> {

    PCI getId();

    NC[] getNodeConfigArray();

    EC[] getEdgeConfigArray();

    NC[] createNodeConfigArray(int length);

    EC[] createEdgeConfigArray(int length);

}
