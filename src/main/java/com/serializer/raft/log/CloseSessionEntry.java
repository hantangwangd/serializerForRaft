package com.serializer.raft.log;

import com.serializer.utils.TimestampPrinter;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Objects;

public class CloseSessionEntry extends SessionEntry {
    private final boolean expired;
    private final boolean delete;

    public CloseSessionEntry() {
    	super(-1, -1, -1);
    	this.expired = false;
    	this.delete = false;
    }
    
    public CloseSessionEntry(long term, long timestamp, long session, boolean expired, boolean delete) {
        super(term, timestamp, session);
        this.expired = expired;
        this.delete = delete;
    }

    /**
     * Returns whether the session is expired.
     *
     * @return Indicates whether the session is expired.
     */
    public boolean expired() {
        return expired;
    }

    /**
     * Returns whether to delete the service.
     *
     * @return whether to delete the service
     */
    public boolean delete() {
        return delete;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .add("timestamp", new TimestampPrinter(timestamp))
                .add("session", session)
                .add("expired", expired)
                .add("delete", delete)
                .toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, timestamp, session, expired, delete);
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof CloseSessionEntry) {
    		CloseSessionEntry entry = (CloseSessionEntry) object;
    		return entry.term == term
                    && entry.timestamp == timestamp
    				&& entry.session == session
    				&& entry.expired == expired
    				&& entry.delete == delete;
    	}
    	return false;
    }
}