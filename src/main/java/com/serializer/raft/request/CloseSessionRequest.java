package com.serializer.raft.request;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class CloseSessionRequest extends SessionRequest {

    private final boolean delete;
    
    public CloseSessionRequest(long session, boolean delete) {
        super(session);
        this.delete = delete;
    }

    /**
     * Returns whether this request is a delete.
     *
     * @return indicates whether this is a delete request
     */
    public boolean delete() {
        return delete;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), session, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !getClass().isAssignableFrom(object.getClass())) {
            return false;
        }

        CloseSessionRequest request = (CloseSessionRequest) object;
        return request.session == session && request.delete == delete;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("session", session)
                .add("delete", delete)
                .toString();
    }
}
