package com.serializer.raft.log;

import com.serializer.utils.ArraySizeHashPrinter;
import com.serializer.utils.TimestampPrinter;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Arrays;
import java.util.Objects;

public class KeepAliveEntry extends TimestampedEntry {
    private final long[] sessionIds;
    private final long[] commandSequences;
    private final long[] eventIndexes;
    
    public KeepAliveEntry(long term, long timestamp, long[] sessionIds, long[] commandSequences, long[] eventIndexes) {
        super(term, timestamp);
        this.sessionIds = sessionIds;
        this.commandSequences = commandSequences;
        this.eventIndexes = eventIndexes;
    }

    /**
     * Returns the session identifiers.
     *
     * @return The session identifiers.
     */
    public long[] sessionIds() {
        return sessionIds;
    }

    /**
     * Returns the command sequence numbers.
     *
     * @return The command sequence numbers.
     */
    public long[] commandSequenceNumbers() {
        return commandSequences;
    }

    /**
     * Returns the event indexes.
     *
     * @return The event indexes.
     */
    public long[] eventIndexes() {
        return eventIndexes;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .add("timestamp", new TimestampPrinter(timestamp))
                .add("sessionIds", ArraySizeHashPrinter.of(sessionIds))
                .add("commandSequences", ArraySizeHashPrinter.of(commandSequences))
                .add("eventIndexes", ArraySizeHashPrinter.of(eventIndexes))
                .toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, timestamp, eventIndexes);
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof KeepAliveEntry) {
    		KeepAliveEntry entry = (KeepAliveEntry) object;
    		return entry.term == term
                    && entry.timestamp == timestamp
                    && Arrays.equals(sessionIds, entry.sessionIds)
                    && Arrays.equals(commandSequences, entry.commandSequences)
                    && Arrays.equals(eventIndexes, entry.eventIndexes);
    	}
    	return false;
    }
}