package com.serializer.raft.log;

import java.util.Objects;

public class InitializeEntry extends TimestampedEntry {
	
	public InitializeEntry() {
		super(-1, -1);
	}
	
    public InitializeEntry(long term, long timestamp) {
        super(term, timestamp);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, timestamp);
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof InitializeEntry) {
    		InitializeEntry entry = (InitializeEntry) object;
    		return entry.term == term
                    && entry.timestamp == timestamp;
    	}
    	return false;
    }
}
