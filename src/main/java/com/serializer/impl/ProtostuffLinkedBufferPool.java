package com.serializer.impl;

import io.protostuff.LinkedBuffer;

public class ProtostuffLinkedBufferPool extends SerializerIOPool<LinkedBuffer> {
    @Override
    protected LinkedBuffer create(int bufferSize) {
        return LinkedBuffer.allocate(bufferSize > 0 ? bufferSize : LinkedBuffer.DEFAULT_BUFFER_SIZE);
    }

    @Override
    protected boolean recycle(LinkedBuffer buffer) {
        buffer.clear();
        return true;
    }
}
