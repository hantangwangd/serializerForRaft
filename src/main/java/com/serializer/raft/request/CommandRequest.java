package com.serializer.raft.request;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Objects;

import com.serializer.raft.PrimitiveOperation;

public class CommandRequest extends OperationRequest {
	
    public CommandRequest(long session, long sequence, PrimitiveOperation operation) {
        super(session, sequence, operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), session, sequence, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CommandRequest) {
            CommandRequest request = (CommandRequest) object;
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
                .toString();
    }
}
