package com.serializer.raft.log;

import com.serializer.utils.TimestampPrinter;

import static com.google.common.base.MoreObjects.toStringHelper;

public class SessionEntry extends TimestampedEntry {
    protected final long session;

    public SessionEntry(long term, long timestamp, long session) {
        super(term, timestamp);
        this.session = session;
    }

    /**
     * Returns the session ID.
     *
     * @return The session ID.
     */
    public long session() {
        return session;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .add("timestamp", new TimestampPrinter(timestamp))
                .add("session", session)
                .toString();
    }
}