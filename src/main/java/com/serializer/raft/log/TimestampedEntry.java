package com.serializer.raft.log;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.serializer.utils.TimestampPrinter;

public class TimestampedEntry extends RaftLogEntry {
    protected final long timestamp;

    public TimestampedEntry(long term, long timestamp) {
        super(term);
        this.timestamp = timestamp;
    }

    /**
     * Returns the entry timestamp.
     *
     * @return The entry timestamp.
     */
    public long timestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .add("timestamp", new TimestampPrinter(timestamp))
                .toString();
    }
}