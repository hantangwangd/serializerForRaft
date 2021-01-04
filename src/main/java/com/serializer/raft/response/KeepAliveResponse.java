package com.serializer.raft.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.serializer.raft.MemberId;
import com.serializer.raft.RaftError;
import com.serializer.utils.ArraySizeHashPrinter;

import static com.google.common.base.MoreObjects.toStringHelper;

public class KeepAliveResponse extends AbstractRaftResponse {

    private final MemberId leader;
    private final ArrayList<MemberId> members;
    private final long[] sessionIds;

    public KeepAliveResponse(Status status, RaftError error) {
        super(status, error);
        this.leader = null;
        this.members = new ArrayList<>();
        this.sessionIds = null;
    }

    public KeepAliveResponse(Status status, RaftError error, MemberId leader, Collection<MemberId> members, long[] sessionIds) {
        super(status, error);
        this.leader = leader;
        this.members = new ArrayList<>(members == null ? Collections.emptyList() : members);
        this.sessionIds = sessionIds;
    }

    /**
     * Returns the cluster leader.
     *
     * @return The cluster leader.
     */
    public MemberId leader() {
        return leader;
    }

    /**
     * Returns the cluster members.
     *
     * @return The cluster members.
     */
    public Collection<MemberId> members() {
        return members;
    }

    /**
     * Returns the sessions that were successfully kept alive.
     *
     * @return The sessions that were successfully kept alive.
     */
    public long[] sessionIds() {
        return sessionIds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), status, leader, members);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof KeepAliveResponse) {
            KeepAliveResponse response = (KeepAliveResponse) object;
            return response.status == status
                    && Objects.equals(response.leader, leader)
                    && Objects.equals(response.members, members);
        }
        return false;
    }

    @Override
    public String toString() {
        if (status == Status.OK) {
            return toStringHelper(this)
                    .add("status", status)
                    .add("leader", leader)
                    .add("members", members)
                    .add("sessionIds", ArraySizeHashPrinter.of(sessionIds))
                    .toString();
        } else {
            return toStringHelper(this)
                    .add("status", status)
                    .add("error", error)
                    .toString();
        }
    }
}