package com.serializer.raft.request;

import com.serializer.raft.PrimitiveOperation;

public abstract class OperationRequest extends SessionRequest {
	protected final long sequence;
    protected final PrimitiveOperation operation;

    protected OperationRequest(long session, long sequence, PrimitiveOperation operation) {
        super(session);
        this.sequence = sequence;
        this.operation = operation;
    }

    /**
     * Returns the request sequence number.
     *
     * @return The request sequence number.
     */
    public long sequenceNumber() {
        return sequence;
    }

    /**
     * Returns the operation.
     *
     * @return The operation.
     */
    public PrimitiveOperation operation() {
        return operation;
    }
}
