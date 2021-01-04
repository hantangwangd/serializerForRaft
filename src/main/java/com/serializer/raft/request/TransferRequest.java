package com.serializer.raft.request;

import java.util.Objects;

import com.serializer.raft.MemberId;

import static com.google.common.base.MoreObjects.toStringHelper;

public class TransferRequest extends AbstractRaftRequest {

    protected final MemberId member;

    public TransferRequest(MemberId member) {
        this.member = member;
    }

    /**
     * Returns the member to which to transfer.
     *
     * @return The member to which to transfer.
     */
    public MemberId member() {
        return member;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), member);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !getClass().isAssignableFrom(object.getClass())) return false;

        return ((TransferRequest) object).member.equals(member);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("member", member)
                .toString();
    }
}