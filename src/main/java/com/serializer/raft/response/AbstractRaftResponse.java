package com.serializer.raft.response;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Objects;

import com.serializer.raft.RaftError;

public class AbstractRaftResponse implements RaftResponse {
	protected final Status status;
    protected final RaftError error;

    protected AbstractRaftResponse(Status status, RaftError error) {
        this.status = status;
        this.error = error;
    }

    @Override
    public Status status() {
        return status;
    }

    @Override
    public RaftError error() {
        return error;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), status);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !getClass().isAssignableFrom(object.getClass())) return false;

        AbstractRaftResponse response = (AbstractRaftResponse) object;
        return response.status == status && Objects.equals(response.error, error);
    }

    @Override
    public String toString() {
        if (status == Status.OK) {
            return toStringHelper(this)
                    .add("status", status)
                    .toString();
        } else {
            return toStringHelper(this)
                    .add("status", status)
                    .add("error", error)
                    .toString();
        }
    }
}
