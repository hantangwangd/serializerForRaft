package com.serializer.raft.response;

import java.util.Objects;

import com.serializer.raft.RaftError;

import static com.google.common.base.MoreObjects.toStringHelper;

public class AppendResponse extends AbstractRaftResponse {

    private final long term;
    private final boolean succeeded;
    private final long lastLogIndex;

    public AppendResponse(Status status, RaftError error) {
        super(status, error);
        term = 0;
        succeeded = true;
        lastLogIndex = 0;
    }

    public AppendResponse(Status status, RaftError error, long term, boolean succeeded, long lastLogIndex) {
        super(status, error);
        this.term = term;
        this.succeeded = succeeded;
        this.lastLogIndex = lastLogIndex;
    }

    /**
     * Returns the requesting node's current term.
     *
     * @return The requesting node's current term.
     */
    public long term() {
        return term;
    }

    /**
     * Returns a boolean indicating whether the append was successful.
     *
     * @return Indicates whether the append was successful.
     */
    public boolean succeeded() {
        return succeeded;
    }

    /**
     * Returns the last index of the replica's log.
     *
     * @return The last index of the responding replica's log.
     */
    public long lastLogIndex() {
        return lastLogIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), status, term, succeeded, lastLogIndex, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof AppendResponse) {
            AppendResponse response = (AppendResponse) object;
            return response.status == status
                    && response.term == term
                    && response.succeeded == succeeded
                    && response.lastLogIndex == lastLogIndex;
        }
        return false;
    }

    @Override
    public String toString() {
        if (status == Status.OK) {
            return toStringHelper(this)
                    .add("status", status)
                    .add("term", term)
                    .add("succeeded", succeeded)
                    .add("lastLogIndex", lastLogIndex)
                    .toString();
        } else {
            return toStringHelper(this)
                    .add("status", status)
                    .add("error", error)
                    .toString();
        }
    }
}