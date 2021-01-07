package com.serializer.raft.response;

import java.util.Objects;

import com.serializer.raft.RaftError;

import static com.google.common.base.MoreObjects.toStringHelper;

public class PollResponse extends AbstractRaftResponse {

    private final long term;
    private final boolean accepted;

    public PollResponse(Status status, RaftError error) {
        super(status, error);
        this.term = 0;
        this.accepted = false;
    }

    public PollResponse(Status status, RaftError error, long term, boolean accepted) {
        super(status, error);
        this.term = term;
        this.accepted = accepted;
    }

    /**
     * Returns the responding node's current term.
     *
     * @return The responding node's current term.
     */
    public long term() {
        return term;
    }

    /**
     * Returns a boolean indicating whether the poll was accepted.
     *
     * @return Indicates whether the poll was accepted.
     */
    public boolean accepted() {
        return accepted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), status, term, accepted, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof PollResponse) {
            PollResponse response = (PollResponse) object;
            return response.status == status
                    && response.term == term
                    && response.accepted == accepted;
        }
        return false;
    }

    @Override
    public String toString() {
        if (status == Status.OK) {
            return toStringHelper(this)
                    .add("status", status)
                    .add("term", term)
                    .add("accepted", accepted)
                    .toString();
        } else {
            return toStringHelper(this)
                    .add("status", status)
                    .add("error", error)
                    .toString();
        }
    }
}