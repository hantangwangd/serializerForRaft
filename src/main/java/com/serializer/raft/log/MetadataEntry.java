package com.serializer.raft.log;

import java.util.Objects;

public class MetadataEntry extends SessionEntry {
	
	public MetadataEntry() {
		super(-1, -1, -1);
	}
	
    public MetadataEntry(long term, long timestamp, long session) {
        super(term, timestamp, session);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, timestamp, session, ranId);
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof MetadataEntry) {
    		MetadataEntry entry = (MetadataEntry) object;
    		return entry.term == term
                    && entry.timestamp == timestamp
    				&& entry.session == session;
    	}
    	return false;
    }
}
