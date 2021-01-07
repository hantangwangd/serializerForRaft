package com.serializer.raft.log;

import java.util.Objects;

import com.serializer.raft.PrimitiveOperation;

public class QueryEntry extends OperationEntry {
	
	public QueryEntry() {
		super(-1, -1, -1, -1, null);
	}
	
    public QueryEntry(long term, long timestamp, long session, long sequence, PrimitiveOperation operation) {
        super(term, timestamp, session, sequence, operation);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, timestamp, session, sequence, operation, ranId);
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof QueryEntry) {
    		QueryEntry entry = (QueryEntry) object;
    		return entry.term == term
                    && entry.timestamp == timestamp
    				&& entry.session == session
    				&& entry.sequence == sequence
    				&& entry.operation.equals(operation);
    	}
    	return false;
    }
}