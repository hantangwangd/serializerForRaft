package com.serializer.raft.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import com.serializer.raft.RaftError;
import com.serializer.raft.RaftMember;

import static com.google.common.base.MoreObjects.toStringHelper;

public class ConfigurationResponse extends AbstractRaftResponse {
    protected final long index;
    protected final long term;
    protected final long timestamp;
    protected final ArrayList<RaftMember> members;

    public ConfigurationResponse(Status status, RaftError error) {
        super(status, error);
        this.index = 0;
        this.term = 0;
        this.timestamp = 0;
        this.members = new ArrayList<>();
    }

    public ConfigurationResponse(Status status, RaftError error, long index, long term, long timestamp, Collection<RaftMember> members) {
        super(status, error);
        this.index = index;
        this.term = term;
        this.timestamp = timestamp;
        if (members != null) {
            this.members = new ArrayList<>(members);
        } else {
            this.members = null;
        }
    }

    /**
     * Returns the response index.
     *
     * @return The response index.
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

    /**
     * Returns the response configuration time.
     *
     * @return The response time.
     */
    public long timestamp() {
        return timestamp;
    }

    /**
     * Returns the configuration members list.
     *
     * @return The configuration members list.
     */
    public Collection<RaftMember> members() {
        return members;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), status, index, term, members, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !getClass().isAssignableFrom(object.getClass())) return false;

        ConfigurationResponse response = (ConfigurationResponse) object;
        return response.status == status
                && response.index == index
                && response.term == term
                && response.timestamp == timestamp
                && response.members.equals(members);
    }

    @Override
    public String toString() {
        if (status == Status.OK) {
            return toStringHelper(this)
                    .add("status", status)
                    .add("index", index)
                    .add("term", term)
                    .add("timestamp", timestamp)
                    .add("members", members)
                    .toString();
        } else {
            return toStringHelper(this)
                    .add("status", status)
                    .add("error", error)
                    .toString();
        }
    }

    @Override
    public void random() {
        super.random();
        for (RaftMember member : members) {
            member.random();
        }
    }
}