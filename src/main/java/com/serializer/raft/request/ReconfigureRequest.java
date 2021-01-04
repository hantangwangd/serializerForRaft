package com.serializer.raft.request;

import java.util.Objects;

import com.serializer.raft.RaftMember;

import static com.google.common.base.MoreObjects.toStringHelper;

public class ReconfigureRequest extends ConfigurationRequest {

    private final long index;
    private final long term;

    public ReconfigureRequest(RaftMember member, long index, long term) {
        super(member);
        this.index = index;
        this.term = term;
    }

    /**
     * Returns the configuration index.
     *
     * @return The configuration index.
     */
    public long index() {
        return index;
    }

    /**
     * Returns the configuration term.
     *
     * @return The configuration term.
     */
    public long term() {
        return term;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), index, member);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ReconfigureRequest) {
            ReconfigureRequest request = (ReconfigureRequest) object;
            return request.index == index && request.term == term && request.member.equals(member);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("index", index)
                .add("term", term)
                .add("member", member)
                .toString();
    }
}