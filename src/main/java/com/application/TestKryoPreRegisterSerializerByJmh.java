package com.application;

import com.serializer.MySerializer;
import com.serializer.raft.creator.TestSerializerCreatorFactory;
import org.openjdk.jmh.annotations.Threads;

@Threads(8)
public class TestKryoPreRegisterSerializerByJmh extends TestSerializerByJmhBase {
    static MySerializer singletonSerializer = TestSerializerCreatorFactory.kryoSerializerNotWriteClassCreator.INSTANCE.create();
    MySerializer multitonSerializer = TestSerializerCreatorFactory.kryoSerializerNotWriteClassCreator.INSTANCE.create();

    public TestKryoPreRegisterSerializerByJmh() {
        init(singletonSerializer);
    }

    @Override
    public MySerializer getMultitonSerializer() {
        return multitonSerializer;
    }

    @Override
    public MySerializer getSingletonSerializer() {
        return singletonSerializer;
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }
}
