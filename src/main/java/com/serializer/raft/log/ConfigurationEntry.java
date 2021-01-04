package com.serializer.raft.log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import com.serializer.raft.RaftMember;
import com.serializer.utils.TimestampPrinter;

import static com.google.common.base.MoreObjects.toStringHelper;

public class ConfigurationEntry extends TimestampedEntry {
    protected final ArrayList<RaftMember> members;

    public ConfigurationEntry() {
    	super(-1, -1);
    	this.members = null;
    }
    
    public ConfigurationEntry(long term, long timestamp, ArrayList<RaftMember> members) {
        super(term, timestamp);
        this.members = members;
    }

    /**
     * Returns the members.
     *
     * @return The members.
     */
    public ArrayList<RaftMember> members() {
        return members;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .add("timestamp", new TimestampPrinter(timestamp))
                .add("members", members)
                .toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, timestamp, members);
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof ConfigurationEntry) {
    		ConfigurationEntry entry = (ConfigurationEntry) object;
    		boolean flag = entry.term == term
                    && entry.timestamp == timestamp;
            if (flag) {
            	if (entry.members == null && members == null) {
            		return true;
            	}
            	
            	if (entry.members.size() == members.size()) {
            		for (int i = 0; i < members.size(); i++) {
            			RaftMember member1 = entry.members.get(i);
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
}