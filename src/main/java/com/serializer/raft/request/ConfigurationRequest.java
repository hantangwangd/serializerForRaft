package com.serializer.raft.request;

import java.util.Objects;

import com.serializer.raft.RaftMember;

import static com.google.common.base.MoreObjects.toStringHelper;

public class ConfigurationRequest extends AbstractRaftRequest {
    protected final RaftMember member;

    protected ConfigurationRequest(RaftMember member) {
        this.member = member;
    }

    /**
     * Returns the member to configure.
     *
     * @return The member to configure.
     */
    public RaftMember member() {
        return member;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), member, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !getClass().isAssignableFrom(object.getClass())) return false;

        return ((ConfigurationRequest) object).member.equals(member);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("member", member)
                .toString();
    }

    @Override
    public void random() {
        super.random();
        member.random();
    }
}
