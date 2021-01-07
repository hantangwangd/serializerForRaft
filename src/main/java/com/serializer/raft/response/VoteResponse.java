package com.serializer.raft.response;

import java.util.Objects;

import com.serializer.raft.RaftError;

import static com.google.common.base.MoreObjects.toStringHelper;

public class VoteResponse extends AbstractRaftResponse {

    private final long term;
    private final boolean voted;

    public VoteResponse(Status status, RaftError error) {
        super(status, error);
        this.term = 0;
        this.voted = false;
    }

    public VoteResponse(Status status, RaftError error, long term, boolean voted) {
        super(status, error);
        this.term = term;
        this.voted = voted;
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
     * Returns a boolean indicating whether the vote was granted.
     *
     * @return Indicates whether the vote was granted.
     */
    public boolean voted() {
        return voted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), status, term, voted, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof VoteResponse) {
            VoteResponse response = (VoteResponse) object;
            return response.status == status
                    && response.term == term
                    && response.voted == voted;
        }
        return false;
    }

    @Override
    public String toString() {
        if (status == Status.OK) {
            return toStringHelper(this)
                    .add("status", status)
                    .add("term", term)
                    .add("voted", voted)
                    .toString();
        } else {
            return toStringHelper(this)
                    .add("status", status)
                    .add("error", error)
                    .toString();
        }
    }
}