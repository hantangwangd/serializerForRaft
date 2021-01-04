package com.serializer.raft.request;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class SessionRequest extends AbstractRaftRequest {
	protected final long session;

    protected SessionRequest(long session) {
        this.session = session;
    }

    /**
     * Returns the session ID.
     *
     * @return The session ID.
     */
    public long session() {
        return session;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), session);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !getClass().isAssignableFrom(object.getClass())) return false;

        SessionRequest request = (SessionRequest) object;
        return request.session == session;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("session", session)
                .toString();
    }
}
