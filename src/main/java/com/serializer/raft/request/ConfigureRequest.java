package com.serializer.raft.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import com.serializer.raft.MemberId;
import com.serializer.raft.RaftMember;

import static com.google.common.base.MoreObjects.toStringHelper;

public class ConfigureRequest extends AbstractRaftRequest {

    private final long term;
    private final String leader;
    private final long index;
    private final long timestamp;
    private final ArrayList<RaftMember> members;
    
    public ConfigureRequest(long term, String leader, long index, long timestamp, Collection<RaftMember> members) {
        this.term = term;
        this.leader = leader;
        this.index = index;
        this.timestamp = timestamp;
        this.members = members == null ? new ArrayList<>() : new ArrayList<>(members);
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
     * Returns the requesting leader address.
     *
     * @return The leader's address.
     */
    public MemberId leader() {
        return MemberId.from(leader);
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
     * Returns the configuration timestamp.
     *
     * @return The configuration timestamp.
     */
    public long timestamp() {
        return timestamp;
    }

    /**
     * Returns the configuration members.
     *
     * @return The configuration members.
     */
    public ArrayList<RaftMember> members() {
        return members;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, leader, index, members, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ConfigureRequest) {
            ConfigureRequest request = (ConfigureRequest) object;
            boolean flag = request.term == term
                    && request.leader.equals(leader)
                    && request.index == index
                    && request.timestamp == timestamp;
            if (flag) {
            	if (request.members == null && members == null) {
            		return true;
            	}
            	
            	if (request.members.size() == members.size()) {
            		for (int i = 0; i < members.size(); i++) {
            			RaftMember member1 = request.members.get(i);
            			RaftMember member2 = members.get(i);
            			if (!member1.equals(member2)) {
            				return false;
            			}
            		}
            		return true;
            	}
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .add("leader", leader)
                .add("index", index)
                .add("timestamp", timestamp)
                .add("members", members)
                .toString();
    }

    @Override
    public void random() {
        super.random();
        for (RaftMember member : members) {
            member.random();
        }
    }
}
