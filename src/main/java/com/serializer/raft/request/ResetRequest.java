package com.serializer.raft.request;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class ResetRequest extends SessionRequest {
    private final long index;

    public ResetRequest(long session, long index) {
        super(session);
        this.index = index;
    }

    /**
     * Returns the event index.
     *
     * @return The event index.
     */
    public long index() {
        return index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), session, index, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ResetRequest) {
            ResetRequest request = (ResetRequest) object;
            return request.session == session
                    && request.index == index;
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("session", session)
                .add("index", index)
                .toString();
    }
}