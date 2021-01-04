package com.serializer.impl;

import com.esotericsoftware.kryo.io.Output;

/**
 * Convenience class to avoid extra object allocation and casting.
 */
public class ByteArrayOutput extends Output {

    private final BufferAwareByteArrayOutputStream stream;

    ByteArrayOutput(final int bufferSize, final int maxBufferSize, final BufferAwareByteArrayOutputStream stream) {
        super(bufferSize, maxBufferSize);
        super.setOutputStream(stream);
        this.stream = stream;
    }

    BufferAwareByteArrayOutputStream getByteArrayOutputStream() {
        return stream;
    }
}