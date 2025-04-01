package org.ss.simpleflow.core.impl.processengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.ListMap;
import org.ss.simpleflow.core.aspect.SfEdgeAspect;
import org.ss.simpleflow.core.aspect.SfNodeAspect;
import org.ss.simpleflow.core.aspect.SfProcessAspect;
import org.ss.simpleflow.core.context.*;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.factory.*;
import org.ss.simpleflow.core.index.SfIndexEntry;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processengine.SfComponentExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessExecutionIdGenerator;

import java.util.*;

public class SfDefaultProcessEngine<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI>
        implements SfProcessEngine<NI, EI, PCI, NC, EC,
        PC, NEI, EEI, PEI> {

    private static final Logger LOG = LoggerFactory.getLogger(SfDefaultProcessEngine.class);

    private final SfProcessEngineConfig processEngineConfig;

    private final SfControlEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> controlEdgeFactory;
    private final SfDataEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> dataEdgeFactory;
    private final SfEventFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> eventFactory;
    private final SfNodeFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> nodeFactory;
    private final SfEnumGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> enumGatewayFactory;
    private final SfStreamIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> streamIteratorFactory;
    private final SfGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> gatewayFactory;
    private final SfAroundIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> aroundIteratorFactory;
    private final SfComponentExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> componentExecutionIdGenerator;
    private final SfProcessExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, PEI> processExecutionIdGenerator;

    private final SfContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> contextFactory;

    private final List<SfEdgeAspect<NI, EI, PCI, NC, EC, PC, EEI, PEI>> edgeAspectList;
    private final List<SfNodeAspect<NI, EI, PCI, NC, EC, PC, NEI, PEI>> nodeAspectList;
    private final List<SfProcessAspect<NI, EI, PCI, NC, EC, PC, PEI>> processAspectList;

    SfDefaultProcessEngine(SfProcessEngineConfig processEngineConfig,
                           SfControlEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> controlEdgeFactory,
                           SfDataEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> dataEdgeFactory,
                           SfEventFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> eventFactory,
                           SfNodeFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> nodeFactory,
                           SfEnumGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> enumGatewayFactory,
                           SfStreamIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> streamIteratorFactory,
                           SfGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> gatewayFactory,
                           SfAroundIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> aroundIteratorFactory,
                           SfComponentExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> componentExecutionIdGenerator,
                           SfProcessExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, PEI> processExecutionIdGenerator,
                           SfContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> contextFactory,
                           List<SfEdgeAspect<NI, EI, PCI, NC, EC, PC, EEI, PEI>> edgeAspectList,
                           List<SfNodeAspect<NI, EI, PCI, NC, EC, PC, NEI, PEI>> nodeAspectList,
                           List<SfProcessAspect<NI, EI, PCI, NC, EC, PC, PEI>> processAspectList) {
        if (processEngineConfig == null) {
            throw new IllegalArgumentException("SfProcessEngineConfig can not be null");
        }
        this.processEngineConfig = processEngineConfig;

        if (controlEdgeFactory == null) {
            throw new IllegalArgumentException("SfControlEdgeFactory can not be null");
        }
        this.controlEdgeFactory = controlEdgeFactory;

        if (dataEdgeFactory == null) {
            throw new IllegalArgumentException("SfDataEdgeFactory can not be null");
        }
        this.dataEdgeFactory = dataEdgeFactory;

        if (eventFactory == null) {
            throw new IllegalArgumentException("SfEventFactory can not be null");
        }
        this.eventFactory = eventFactory;

        if (nodeFactory == null) {
            throw new IllegalArgumentException("SfNodeFactory can not be null");
        }
        this.nodeFactory = nodeFactory;

        if (enumGatewayFactory == null) {
            throw new IllegalArgumentException("SfEnumGatewayFactory can not be null");
        }
        this.enumGatewayFactory = enumGatewayFactory;

        if (streamIteratorFactory == null) {
            throw new IllegalArgumentException("SfStreamIteratorFactory can not be null");
        }
        this.streamIteratorFactory = streamIteratorFactory;

        if (gatewayFactory == null) {
            throw new IllegalArgumentException("SfGatewayFactory can not be null");
        }
        this.gatewayFactory = gatewayFactory;

        if (aroundIteratorFactory == null) {
            throw new IllegalArgumentException("SfAroundIteratorFactory can not be null");
        }
        this.aroundIteratorFactory = aroundIteratorFactory;

        if (componentExecutionIdGenerator == null) {
            throw new IllegalArgumentException("SfComponentExecutionIdGenerator can not be null");
        }
        this.componentExecutionIdGenerator = componentExecutionIdGenerator;

        if (processExecutionIdGenerator == null) {
            throw new IllegalArgumentException("SfProcessExecutionIdGenerator can not be null");
        }
        this.processExecutionIdGenerator = processExecutionIdGenerator;

        if (contextFactory == null) {
            throw new IllegalArgumentException("SfContextFactory can not be null");
        }
        this.contextFactory = contextFactory;

        this.edgeAspectList = edgeAspectList;
        this.nodeAspectList = nodeAspectList;
        this.processAspectList = processAspectList;
    }

    @Override
    public final SfProcessExecuteResult<PEI> executeProcess(SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
                                                            PEI processExecutionId,
                                                            ListMap<String, Object> params,
                                                            Map<String, Object> processVariable) {
        SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData = wholePreprocessData.getMainProcessPreprocessData();
        SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> mainProcessContext = contextFactory.createProcessContext();
        PC mainProcessConfig = mainProcessPreprocessData.getProcessConfig();
        mainProcessContext.setProcessConfig(mainProcessConfig);
        mainProcessContext.getVariables().putAll(processVariable);
        if (processExecutionId != null) {
            PEI generateProcessExecutionId = processExecutionIdGenerator.generateProcessExecutionId(mainProcessContext);
            mainProcessContext.setProcessExecutionId(generateProcessExecutionId);
        }
        mainProcessContext.setRootProcessContext(mainProcessContext);
        mainProcessContext.setRoot(true);

        SfWholeExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> wholeExecutionContext = contextFactory.createWholeExecutionContext();
        SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainProcessExecutionContext = contextFactory.createProcessExecutionContext();
        wholeExecutionContext.setMainProcessExecuteContext(mainProcessExecutionContext);

        mainProcessExecutionContext.setProcessContext(mainProcessContext);
        List<SfNodeContext<NI, PCI, NEI, NC>> nodeContextList = new ArrayList<>();
        mainProcessExecutionContext.setNodeContextList(nodeContextList);


        if (CollectionUtils.isNotEmpty(processAspectList)) {
            for (SfProcessAspect<NI, EI, PCI, NC, EC, PC, PEI> processAspect : processAspectList) {
                processAspect.before(params, mainProcessContext);
            }
        }

        int startNodeConfigIndex = mainProcessPreprocessData.getStartNodeConfigIndex();
        Deque<SfIndexEntry> stack = new ArrayDeque<>();
        List<SfIndexEntry> nodeIndexEntryList = mainProcessPreprocessData.getNodeIndexEntryList();
        SfIndexEntry indexEntry = nodeIndexEntryList.get(startNodeConfigIndex);
        stack.push(indexEntry);

        return null;
    }

    private void executeProcess(SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> processPreprocessData,
                                SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> processExecutionContext,
                                Deque<SfIndexEntry> stack) {
        List<SfIndexEntry> nodeIndexEntryList = processPreprocessData.getNodeIndexEntryList();
        List<SfIndexEntry> edgeIndexEntryList = processPreprocessData.getEdgeIndexEntryList();
        PC processConfig = processPreprocessData.getProcessConfig();
        List<NC> nodeConfigList = processConfig.getNodeConfigList();
        while (!stack.isEmpty()) {
            SfIndexEntry pop = stack.pop();
            if (pop.isNode()) {


            } else {

            }
        }
    }

    @Override
    public final SfProcessExecuteResult<PEI> executeProcess(SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
                                                            ListMap<String, Object> params,
                                                            Map<String, Object> processVariable) {
        return executeProcess(wholePreprocessData, null, params, processVariable);
    }

    @Override
    public final SfProcessExecuteResult<PEI> executeProcess(SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
                                                            ListMap<String, Object> params) {
        return executeProcess(wholePreprocessData, params, null);
    }

}
