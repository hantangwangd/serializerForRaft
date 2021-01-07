package com.serializer.raft.request;

import java.util.Arrays;
import java.util.Objects;

import com.serializer.utils.ArraySizeHashPrinter;

import static com.google.common.base.MoreObjects.toStringHelper;

public class KeepAliveRequest extends AbstractRaftRequest {

    private final long[] sessionIds;
    private final long[] commandSequences;
    private final long[] eventIndexes;
    
    public KeepAliveRequest(long[] sessionIds, long[] commandSequences, long[] eventIndexes) {
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
    public int hashCode() {
        return Objects.hash(getClass(), sessionIds, commandSequences, eventIndexes, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof KeepAliveRequest) {
            KeepAliveRequest request = (KeepAliveRequest) object;
            return Arrays.equals(request.sessionIds, sessionIds)
                    && Arrays.equals(request.commandSequences, commandSequences)
                    && Arrays.equals(request.eventIndexes, eventIndexes);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("sessionIds", ArraySizeHashPrinter.of(sessionIds))
                .add("commandSequences", ArraySizeHashPrinter.of(commandSequences))
                .add("eventIndexes", ArraySizeHashPrinter.of(eventIndexes))
                .toString();
    }
}