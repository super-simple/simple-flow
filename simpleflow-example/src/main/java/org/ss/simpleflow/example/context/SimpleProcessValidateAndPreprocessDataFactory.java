package org.ss.simpleflow.example.context;

import org.ss.simpleflow.core.context.SfAbstractProcessPreprocessData;
import org.ss.simpleflow.core.context.SfValidationProcessContext;
import org.ss.simpleflow.core.context.SfValidationWholeContext;
import org.ss.simpleflow.core.context.SfWholePreprocessData;
import org.ss.simpleflow.core.factory.SfProcessValidateAndPreprocessDataFactory;
import org.ss.simpleflow.example.config.SimpleEdgeConfig;
import org.ss.simpleflow.example.config.SimpleNodeConfig;
import org.ss.simpleflow.example.config.SimpleProcessConfig;
import org.ss.simpleflow.example.context.preprocess.AdaptProcessPreprocessData;
import org.ss.simpleflow.example.context.preprocess.AdaptValidationProcessContext;
import org.ss.simpleflow.example.context.preprocess.AdaptValidationWholeContext;
import org.ss.simpleflow.example.context.preprocess.AdaptWholePreprocessData;

public class SimpleProcessValidateAndPreprocessDataFactory
        implements SfProcessValidateAndPreprocessDataFactory<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig> {

    @Override
    public SfWholePreprocessData<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig> createWholePreprocessData() {
        return new AdaptWholePreprocessData();
    }

    @Override
    public SfAbstractProcessPreprocessData<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig> createProcessPreprocessData() {
        return new AdaptProcessPreprocessData();
    }

    @Override
    public SfValidationWholeContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig> createValidationWholeContext() {
        return new AdaptValidationWholeContext();
    }

    @Override
    public SfValidationProcessContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig> createValidationProcessContext() {
        return new AdaptValidationProcessContext();
    }

}
