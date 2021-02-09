package com.application;

import com.serializer.MySerializer;
import com.serializer.raft.creator.TestSerializerCreatorFactory;
import org.openjdk.jmh.annotations.Threads;

@Threads(8)
public class TestProtostuffSerializerByJmh extends TestSerializerByJmhBase {
    static MySerializer singletonSerializer = TestSerializerCreatorFactory.ProtostuffSerializerCreator.INSTANCE.create();
    MySerializer multitonSerializer = TestSerializerCreatorFactory.ProtostuffSerializerCreator.INSTANCE.create();

    public TestProtostuffSerializerByJmh() {
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
