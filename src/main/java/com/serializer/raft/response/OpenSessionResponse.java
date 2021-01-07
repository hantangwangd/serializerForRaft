package com.serializer.raft.response;

import java.util.Objects;

import com.serializer.raft.RaftError;

import static com.google.common.base.MoreObjects.toStringHelper;

public class OpenSessionResponse extends AbstractRaftResponse {

    protected final long session;
    protected final long timeout;

    public OpenSessionResponse(Status status, RaftError error) {
        super(status, error);
        this.session = 0;
        this.timeout = 0;
    }

    public OpenSessionResponse(Status status, RaftError error, long session, long timeout) {
        super(status, error);
        this.session = session;
        this.timeout = timeout;
    }

    /**
     * Returns the registered session ID.
     *
     * @return The registered session ID.
     */
    public long session() {
        return session;
    }

    /**
     * Returns the session timeout.
     *
     * @return The session timeout.
     */
    public long timeout() {
        return timeout;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), error, status, session, timeout, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof OpenSessionResponse) {
            OpenSessionResponse response = (OpenSessionResponse) object;
            return response.status == status
                    && Objects.equals(response.error, error)
                    && response.session == session
                    && response.timeout == timeout;
        }
        return false;
    }

    @Override
    public String toString() {
        if (status == Status.OK) {
            return toStringHelper(this)
                    .add("status", status)
                    .add("session", session)
                    .add("timeout", timeout)
                    .toString();
        } else {
            return toStringHelper(this)
                    .add("status", status)
                    .add("error", error)
                    .toString();
        }
    }
}
