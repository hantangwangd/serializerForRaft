package com.serializer.impl;

import java.io.ByteArrayOutputStream;

/**
 * Exposes protected byte array length in {@link ByteArrayOutputStream}.
 */
public class BufferAwareByteArrayOutputStream extends ByteArrayOutputStream {

    BufferAwareByteArrayOutputStream(int size) {
        super(size);
    }

    int getBufferSize() {
        return buf.length;
    }
}