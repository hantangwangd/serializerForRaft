package com.serializer.raft.request;

import java.util.Objects;

import com.serializer.raft.PrimitiveOperation;

import static com.google.common.base.MoreObjects.toStringHelper;

public class QueryRequest extends OperationRequest {

    private final long index;
    
    public QueryRequest(long session, long sequence, PrimitiveOperation operation, long index) {
        super(session, sequence, operation);
        this.index = index;
    }

    /**
     * Returns the query index.
     *
     * @return The query index.
     */
    public long index() {
        return index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), session, sequence, operation, index);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof QueryRequest) {
            QueryRequest request = (QueryRequest) object;
            return request.session == session
                    && request.sequence == sequence
                    && request.operation.equals(operation);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("session", session)
                .add("sequence", sequence)
                .add("operation", operation)
                .add("index", index)
                .toString();
    }
}
