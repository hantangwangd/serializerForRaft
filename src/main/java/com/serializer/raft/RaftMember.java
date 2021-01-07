package com.serializer.raft;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

import com.google.common.hash.Hashing;

public class RaftMember implements Serializable {

	/**
	 * Indicates how the member participates in voting and replication.
	 * <p>
	 * The member type defines how a member interacts with the other members of the
	 * cluster and, more importantly, how the cluster {@link RaftCluster#getLeader()
	 * leader} interacts with the member server. Members can be {@link #promote()
	 * promoted} and {@link #demote() demoted} to alter member states. See the
	 * specific member types for descriptions of their implications on the cluster.
	 */
	public enum Type {

		/**
		 * Represents an inactive member.
		 * <p>
		 * The {@code INACTIVE} member type represents a member which does not
		 * participate in any communication and is not an active member of the cluster.
		 * This is typically the state of a member prior to joining or after leaving a
		 * cluster.
		 */
		INACTIVE,

		/**
		 * Represents a member which participates in asynchronous replication but does
		 * not vote in elections or otherwise participate in the Raft consensus
		 * algorithm.
		 * <p>
		 * The {@code PASSIVE} member type is representative of a member that receives
		 * state changes from follower nodes asynchronously. As state changes are
		 * committed via the {@link #ACTIVE} Raft nodes, committed state changes are
		 * asynchronously replicated by followers to passive members. This allows
		 * passive members to maintain nearly up-to-date state with minimal impact on
		 * the performance of the Raft algorithm itself, and allows passive members to
		 * be quickly promoted to {@link #ACTIVE} voting members if necessary.
		 */
		PASSIVE,

		/**
		 * Represents a non-voting member being caught up to the leader for promotion.
		 * <p>
		 * This state is used to replicate committed and uncommitted entries to a node
		 * in the process of being promoted to {@link #ACTIVE}. It allows a node to be
		 * caught up to the leader prior to becoming a voting member to avoid blocking
		 * the cluster.
		 */
		PROMOTABLE,

		/**
		 * Represents a full voting member of the Raft cluster which participates fully
		 * in leader election and replication algorithms.
		 * <p>
		 * The {@code ACTIVE} member type represents a full voting member of the Raft
		 * cluster. Active members participate in the Raft leader election and
		 * replication algorithms and can themselves be elected leaders.
		 */
		ACTIVE,

	}
	
    private final MemberId id;
	private final int hash;
    private Type type;
    private long updated;

	transient Random ran = new Random();
	long ranId = ran.nextInt(10000);
    
    public RaftMember(MemberId id, Type type, long updated) {
        this.id = checkNotNull(id, "id cannot be null");
        this.hash = Hashing.murmur3_32()
                .hashUnencodedChars(id.id())
                .asInt();
        this.type = checkNotNull(type, "type cannot be null");
        this.updated = checkNotNull(updated, "updated cannot be null");
    }

    public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public long getUpdated() {
		return updated;
	}

	public void setUpdated(long updated) {
		this.updated = updated;
	}

	public MemberId getId() {
		return id;
	}

	public int getHash() {
		return hash;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(getClass(), id, ranId);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof RaftMember && ((RaftMember) object).id.equals(id);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", id)
                .add("type", type)
                .add("updated", updated)
                .toString();
    }

	public void random() {
		ranId = ran.nextInt(10000);
	}
	
}
