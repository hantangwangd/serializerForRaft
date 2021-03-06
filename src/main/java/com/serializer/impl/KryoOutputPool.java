package com.serializer.impl;

public class KryoOutputPool extends SerializerIOPool<ByteArrayOutput> {

    private static final int MAX_BUFFER_SIZE = 768 * 1024;
    static final int MAX_POOLED_BUFFER_SIZE = 512 * 1024;

    @Override
    protected ByteArrayOutput create(int bufferSize) {
        return new ByteArrayOutput(bufferSize, MAX_BUFFER_SIZE, new BufferAwareByteArrayOutputStream(bufferSize));
    }

    @Override
    protected boolean recycle(ByteArrayOutput output) {
        if (output.getByteArrayOutputStream().getBufferSize() < MAX_POOLED_BUFFER_SIZE) {
            output.getByteArrayOutputStream().reset();
            output.clear();
            return true;
        }
        return false; // discard
    }
}