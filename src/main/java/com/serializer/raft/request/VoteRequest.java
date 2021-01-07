package com.serializer.raft.request;

import java.util.Objects;

import com.serializer.raft.MemberId;

import static com.google.common.base.MoreObjects.toStringHelper;

public class VoteRequest extends AbstractRaftRequest {

    private final long term;
    private final String candidate;
    private final long lastLogIndex;
    private final long lastLogTerm;

    public VoteRequest(long term, String candidate, long lastLogIndex, long lastLogTerm) {
        this.term = term;
        this.candidate = candidate;
        this.lastLogIndex = lastLogIndex;
        this.lastLogTerm = lastLogTerm;
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
     * Returns the candidate's address.
     *
     * @return The candidate's address.
     */
    public MemberId candidate() {
        return MemberId.from(candidate);
    }

    /**
     * Returns the candidate's last log index.
     *
     * @return The candidate's last log index.
     */
    public long lastLogIndex() {
        return lastLogIndex;
    }

    /**
     * Returns the candidate's last log term.
     *
     * @return The candidate's last log term.
     */
    public long lastLogTerm() {
        return lastLogTerm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, candidate, lastLogIndex, lastLogTerm, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof VoteRequest) {
            VoteRequest request = (VoteRequest) object;
            return request.term == term
                    && request.candidate.equals(candidate)
                    && request.lastLogIndex == lastLogIndex
                    && request.lastLogTerm == lastLogTerm;
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .add("candidate", candidate)
                .add("lastLogIndex", lastLogIndex)
                .add("lastLogTerm", lastLogTerm)
                .toString();
    }
}