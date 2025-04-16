package org.ss.simpleflow.core.impl.processengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ss.simpleflow.core.aspect.SfEdgeAspect;
import org.ss.simpleflow.core.aspect.SfNodeAspect;
import org.ss.simpleflow.core.aspect.SfProcessAspect;
import org.ss.simpleflow.core.constant.SfNodeTypeConstant;
import org.ss.simpleflow.core.context.*;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.edge.SfControlEdge;
import org.ss.simpleflow.core.edge.SfDataEdge;
import org.ss.simpleflow.core.event.SfEvent;
import org.ss.simpleflow.core.factory.*;
import org.ss.simpleflow.core.index.SfIndexEntry;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.node.SfNode;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processengine.SfComponentExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessExecutionIdGenerator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class SfDefaultProcessEngine<NI, EI, PCI, NC extends SfAbstractNodeConfig<NI, PCI>, EC extends SfAbstractEdgeConfig<EI, NI>, PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>, NEI, EEI, PEI> implements SfProcessEngine<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> {

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

    private final SfExecutionContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> executionContextFactory;

    private final List<SfEdgeAspect<NI, EI, PCI, NC, EC, PC, EEI, PEI>> edgeAspectList;
    private final List<SfNodeAspect<NI, EI, PCI, NC, EC, PC, NEI, PEI>> nodeAspectList;
    private final List<SfProcessAspect<NI, EI, PCI, NC, EC, PC, PEI>> processAspectList;

    public SfDefaultProcessEngine(SfProcessEngineConfig processEngineConfig,
                                  SfControlEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> controlEdgeFactory,
                                  SfDataEdgeFactory<NI, EI, PCI, NC, EC, PC, EEI, PEI> dataEdgeFactory,
                                  SfEventFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> eventFactory,
                                  SfNodeFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> nodeFactory,
                                  SfEnumGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> enumGatewayFactory,
                                  SfStreamIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> streamIteratorFactory,
                                  SfGatewayFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> gatewayFactory,
                                  SfAroundIteratorFactory<NI, EI, PCI, NC, EC, PC, NEI, PEI> aroundIteratorFactory,
                                  SfProcessExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, PEI> processExecutionIdGenerator,
                                  SfComponentExecutionIdGenerator<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> componentExecutionIdGenerator,
                                  SfExecutionContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> executionContextFactory,
                                  List<SfEdgeAspect<NI, EI, PCI, NC, EC, PC, EEI, PEI>> edgeAspectList,
                                  List<SfNodeAspect<NI, EI, PCI, NC, EC, PC, NEI, PEI>> nodeAspectList,
                                  List<SfProcessAspect<NI, EI, PCI, NC, EC, PC, PEI>> processAspectList) {
        if (processEngineConfig == null) {
            throw new IllegalArgumentException("SfProcessEngineConfig can not be null");
        }
        this.processEngineConfig = processEngineConfig;

        this.controlEdgeFactory = controlEdgeFactory;
        this.dataEdgeFactory = dataEdgeFactory;

        this.eventFactory = eventFactory;
        this.nodeFactory = nodeFactory;
        this.enumGatewayFactory = enumGatewayFactory;
        this.streamIteratorFactory = streamIteratorFactory;
        this.gatewayFactory = gatewayFactory;
        this.aroundIteratorFactory = aroundIteratorFactory;

        if (processExecutionIdGenerator == null) {
            throw new IllegalArgumentException("SfProcessExecutionIdGenerator can not be null");
        }
        this.processExecutionIdGenerator = processExecutionIdGenerator;

        if (componentExecutionIdGenerator == null) {
            throw new IllegalArgumentException("SfComponentExecutionIdGenerator can not be null");
        }
        this.componentExecutionIdGenerator = componentExecutionIdGenerator;

        if (executionContextFactory == null) {
            throw new IllegalArgumentException("SfContextFactory can not be null");
        }
        this.executionContextFactory = executionContextFactory;

        this.edgeAspectList = edgeAspectList;
        this.nodeAspectList = nodeAspectList;
        this.processAspectList = processAspectList;
    }

    @Override
    public final SfProcessExecutionResult<PEI> runProcess(SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
                                                          Map<String, Object> params) {
        return runProcess(wholePreprocessData, params, null, null);
    }

    @Override
    public final SfProcessExecutionResult<PEI> runProcess(SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
                                                          Map<String, Object> params,
                                                          Map<String, Object> processVariable) {
        return runProcess(wholePreprocessData, params, processVariable, null);
    }

    @Override
    public final SfProcessExecutionResult<PEI> runProcess(SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
                                                          Map<String, Object> params,
                                                          Map<String, Object> processVariable,
                                                          PEI processExecutionId) {
        SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData = wholePreprocessData.getMainProcessPreprocessData();
        SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> mainProcessContext = initMainProcessContext(
                mainProcessPreprocessData,
                processVariable,
                processExecutionId);

        SfWholeExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> wholeExecutionContext = initWholeExecutionContext(
                wholePreprocessData,
                mainProcessContext);

        SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainProcessExecuteContext = wholeExecutionContext.getMainProcessExecuteContext();
        mainProcessExecuteContext.getParamArray()[mainProcessPreprocessData.getStartNodeConfigIndex()] = params;

        runProcess(wholePreprocessData, wholeExecutionContext);

        return null;
    }


    private SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> initMainProcessContext(SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData,
                                                                                  Map<String, Object> processVariable,
                                                                                  PEI processExecutionId) {
        SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> mainProcessContext = executionContextFactory.createProcessContext();
        mainProcessContext.getVariables().putAll(processVariable);
        mainProcessContext.setRootProcessContext(mainProcessContext);
        mainProcessContext.setRoot(true);
        if (processExecutionId != null) {
            PEI generateProcessExecutionId = processExecutionIdGenerator.generateProcessExecutionId(
                    mainProcessPreprocessData.getProcessConfig(),
                    mainProcessContext);
            mainProcessContext.setProcessExecutionId(generateProcessExecutionId);
        }
        return mainProcessContext;
    }

    private SfWholeExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> initWholeExecutionContext(
            SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
            SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> mainProcessContext) {
        SfWholeExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> wholeExecutionContext = executionContextFactory.createWholeExecutionContext();
        SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData = wholePreprocessData.getMainProcessPreprocessData();
        SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> mainProcessExecutionContext = initProcessExecutionContext(
                mainProcessPreprocessData,
                mainProcessContext);
        wholeExecutionContext.setMainProcessExecuteContext(mainProcessExecutionContext);
        SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC>[] subProcessPreprocessDataArray = wholePreprocessData.getSubProcessPreprocessDataArray();
        //noinspection unchecked
        wholeExecutionContext.setSubProcessExecutionContextArray(new SfProcessExecutionContext[subProcessPreprocessDataArray.length]);
        return wholeExecutionContext;
    }

    private SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> initProcessExecutionContext(
            SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> processPreprocessData,
            SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext) {
        SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> processExecutionContext = executionContextFactory.createProcessExecutionContext();

        processExecutionContext.setProcessContext(processContext);
        int nodeConfigListSize = processPreprocessData.getNodeConfigListSize();

        //noinspection unchecked
        processExecutionContext.setParamArray(new Map[nodeConfigListSize]);
        //noinspection unchecked
        processExecutionContext.setResultArray(new Map[nodeConfigListSize]);
        //noinspection unchecked
        processExecutionContext.setNodeContextArray(new SfNodeContext[nodeConfigListSize]);

        int edgeConfigListSize = processPreprocessData.getEdgeConfigListSize();
        //noinspection unchecked
        processExecutionContext.setEdgeContextArray(new SfEdgeContext[edgeConfigListSize]);

        return processExecutionContext;
    }

    private void runProcess(SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
                            SfWholeExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> wholeExecutionContext) {
        Deque<Deque<Deque<SfIndexEntry>>> wholeStack = new ArrayDeque<>();
        Deque<Deque<SfIndexEntry>> processStack = new ArrayDeque<>();
        Deque<SfIndexEntry> eventStack = new ArrayDeque<>();

        processStack.push(eventStack);
        wholeStack.push(processStack);

        SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData = wholePreprocessData.getMainProcessPreprocessData();
        int startNodeConfigIndex = mainProcessPreprocessData.getStartNodeConfigIndex();
        List<SfIndexEntry> nodeIndexEntryList = mainProcessPreprocessData.getNodeIndexEntryList();
        SfIndexEntry indexEntry = nodeIndexEntryList.get(startNodeConfigIndex);
        eventStack.push(indexEntry);

        executeProcess(mainProcessPreprocessData, wholeExecutionContext.getMainProcessExecuteContext(), processStack);
    }

    private void executeProcess(SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> processPreprocessData,
                                SfProcessExecutionContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> processExecutionContext,
                                Deque<Deque<SfIndexEntry>> processStack) {
        List<SfIndexEntry> nodeIndexEntryList = processPreprocessData.getNodeIndexEntryList();
        List<SfIndexEntry> edgeIndexEntryList = processPreprocessData.getEdgeIndexEntryList();
        List<List<SfIndexEntry>> allOutgoingControlEdgeList = processPreprocessData.getAllOutgoingControlEdgeList();

        PC processConfig = processPreprocessData.getProcessConfig();
        NC[] nodeConfigArray = processConfig.getNodeConfigArray();
        EC[] edgeConfigArray = processConfig.getEdgeConfigArray();

        SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext = processExecutionContext.getProcessContext();
        SfNodeContext<NI, PCI, NEI, NC>[] nodeContextArray = processExecutionContext.getNodeContextArray();
        SfEdgeContext<NI, EI, EEI, EC>[] edgeContextArray = processExecutionContext.getEdgeContextArray();
        Map<String, Object>[] paramArray = processExecutionContext.getParamArray();
        Map<String, Object>[] resultArray = processExecutionContext.getResultArray();
        while (!processStack.isEmpty()) {
            Deque<SfIndexEntry> eventStack = processStack.pop();
            while (!eventStack.isEmpty()) {
                SfIndexEntry currentIndexEntry = eventStack.pop();
                int selfIndex = currentIndexEntry.getSelfIndex();
                if (currentIndexEntry.isNode()) {
                    NC nc = nodeConfigArray[selfIndex];
                    SfNodeContext<NI, PCI, NEI, NC> nodeContext = nodeContextArray[selfIndex];
                    Map<String, Object> paramMap = paramArray[selfIndex];
                    Map<String, Object> resultMap = executeNode(paramMap,
                                                                nc,
                                                                nodeContext,
                                                                processConfig,
                                                                processContext);

                    resultArray[selfIndex] = resultMap;
                    List<SfIndexEntry> indexEntryList = allOutgoingControlEdgeList.get(selfIndex);
                    if (!indexEntryList.isEmpty()) {
                        for (SfIndexEntry indexEntry : indexEntryList) {
                            eventStack.push(indexEntry);
                        }
                    }
                } else {
                    EC ec = edgeConfigArray[selfIndex];
                    SfEdgeContext<NI, EI, EEI, EC> edgeContext = edgeContextArray[selfIndex];
                    if (edgeContext == null) {
                        edgeContext = executionContextFactory.createEdgeContext();
                        edgeContextArray[selfIndex] = edgeContext;
                    }
                    if (ec.isControlEdge()) {
                        SfControlEdge<NI, EI, PCI, NC, EC, PC, EEI, PEI> controlEdge = controlEdgeFactory.createControlEdge(
                                ec,
                                edgeContext,
                                processConfig,
                                processContext);
                        int fromNodeConfigIndex = currentIndexEntry.getFromNodeConfigIndex();
                        Map<String, Object> fromNodeResultMap = resultArray[fromNodeConfigIndex];
                        try {
                            boolean result = controlEdge.executeControlEdge(fromNodeResultMap,
                                                                            ec,
                                                                            edgeContext,
                                                                            processConfig,
                                                                            processContext);
                            if (result) {
                                int toNodeConfigIndex = currentIndexEntry.getToNodeConfigIndex();
                                SfIndexEntry toNodeIndexEntry = nodeIndexEntryList.get(toNodeConfigIndex);
                                eventStack.push(toNodeIndexEntry);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        if (processEngineConfig.isExecuteOnDataEdge()) {
                            SfDataEdge<NI, EI, PCI, NC, EC, PC, EEI, PEI> dataEdge = dataEdgeFactory.createDataEdge(ec,
                                                                                                                    edgeContext,
                                                                                                                    processConfig,
                                                                                                                    processContext);
                        }
                    }
                }
            }
        }
    }

    private Map<String, Object> executeNode(Map<String, Object> paramMap,
                                            NC nc,
                                            SfNodeContext<NI, PCI, NEI, NC> nodeContext,
                                            PC pc,
                                            SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> processContext) {
        String nodeType = nc.getNodeType();
        Map<String, Object> resultMap;
        switch (nodeType) {
            case SfNodeTypeConstant.EVENT: {
                SfEvent<NI, EI, PCI, NC, EC, PC, NEI, PEI> event = eventFactory.createEvent(nc,
                                                                                            nodeContext,
                                                                                            pc,
                                                                                            processContext);
                try {
                    resultMap = event.executeEvent(paramMap,
                                                   nc,
                                                   nodeContext,
                                                   pc,
                                                   processContext);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case SfNodeTypeConstant.NODE: {
                SfNode<NI, EI, PCI, NC, EC, PC, NEI, PEI> node = nodeFactory.createNode(nc,
                                                                                        nodeContext,
                                                                                        pc,
                                                                                        processContext);
                try {
                    resultMap = node.executeNode(paramMap,
                                                 nc,
                                                 nodeContext,
                                                 pc,
                                                 processContext);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            default: {
                throw new RuntimeException();
            }
        }
        return resultMap;
    }

}
