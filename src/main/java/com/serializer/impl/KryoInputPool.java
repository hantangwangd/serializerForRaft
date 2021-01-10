package com.serializer.impl;

import com.esotericsoftware.kryo.io.Input;

public class KryoInputPool extends SerializerIOPool<Input> {

    static final int MAX_POOLED_BUFFER_SIZE = 512 * 1024;

    @Override
    protected Input create(int bufferSize) {
        return new Input(bufferSize);
    }

    @Override
    protected boolean recycle(Input input) {
        if (input.getBuffer().length < MAX_POOLED_BUFFER_SIZE) {
            input.setInputStream(null);
            return true;
        }
        return false; // discard
    }
}