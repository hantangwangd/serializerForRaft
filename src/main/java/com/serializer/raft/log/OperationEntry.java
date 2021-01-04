package com.serializer.raft.log;

import com.serializer.raft.PrimitiveOperation;
import com.serializer.utils.TimestampPrinter;

import static com.google.common.base.MoreObjects.toStringHelper;

public class OperationEntry extends SessionEntry {
    protected final long sequence;
    protected final PrimitiveOperation operation;

    public OperationEntry(long term, long timestamp, long session, long sequence, PrimitiveOperation operation) {
        super(term, timestamp, session);
        this.sequence = sequence;
        this.operation = operation;
    }

    /**
     * Returns the entry operation.
     *
     * @return The entry operation.
     */
    public PrimitiveOperation operation() {
        return operation;
    }

    /**
     * Returns the operation sequence number.
     *
     * @return The operation sequence number.
     */
    public long sequenceNumber() {
        return sequence;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .add("timestamp", new TimestampPrinter(timestamp))
                .add("session", session)
                .add("sequence", sequence)
                .add("operation", operation)
                .toString();
    }
}
