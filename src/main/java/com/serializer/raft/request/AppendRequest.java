package com.serializer.raft.request;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.List;
import java.util.Objects;

import com.serializer.raft.MemberId;
import com.serializer.raft.log.RaftLogEntry;

//@DefaultSerializer(CompatibleFieldSerializer.class)  
public class AppendRequest extends AbstractRaftRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1231231231L;
	
	private final long term;
    private final String leader;
    private final long prevLogIndex;
    private final long prevLogTerm;
    private final List<RaftLogEntry> entries;
    private final long commitIndex;
    
    public AppendRequest(long term, String leader, long prevLogIndex, long prevLogTerm, List<RaftLogEntry> entries, long commitIndex) {
    	this.term = term;
        this.leader = leader;
        this.prevLogIndex = prevLogIndex;
        this.prevLogTerm = prevLogTerm;
        this.entries = entries;
        this.commitIndex = commitIndex;
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
     * Returns the index of the log entry preceding the new entry.
     *
     * @return The index of the log entry preceding the new entry.
     */
    public long prevLogIndex() {
        return prevLogIndex;
    }

    /**
     * Returns the term of the log entry preceding the new entry.
     *
     * @return The index of the term preceding the new entry.
     */
    public long prevLogTerm() {
        return prevLogTerm;
    }

    /**
     * Returns the log entries to append.
     *
     * @return A list of log entries.
     */
    public List<RaftLogEntry> entries() {
        return entries;
    }

    /**
     * Returns the leader's commit index.
     *
     * @return The leader commit index.
     */
    public long commitIndex() {
        return commitIndex;
    }

    @Override
    public void random() {
        super.random();
        for (RaftLogEntry entry : entries) {
            entry.random();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, leader, prevLogIndex, prevLogTerm, entries, commitIndex, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof AppendRequest) {
            AppendRequest request = (AppendRequest) object;
            boolean flag = request.term == term
                    && request.leader.equals(leader)
                    && request.prevLogIndex == prevLogIndex
                    && request.prevLogTerm == prevLogTerm
                    && request.commitIndex == commitIndex;
            if (flag) {
            	if (request.entries == null && entries == null) {
            		return true;
            	}
            	
            	if (request.entries.size() == entries.size()) {
            		for (int i = 0; i < entries.size(); i++) {
            			RaftLogEntry entry1 = request.entries.get(i);
            			RaftLogEntry entry2 = entries.get(i);
            			if (!entry1.equals(entry2)) {
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
                .add("prevLogIndex", prevLogIndex)
                .add("prevLogTerm", prevLogTerm)
                .add("entries", entries.size())
                .add("commitIndex", commitIndex)
                .toString();
    }
}
