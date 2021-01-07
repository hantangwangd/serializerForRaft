package com.serializer.raft.request;

import java.util.List;
import java.util.Objects;

import com.serializer.raft.PrimitiveEvent;

import static com.google.common.base.MoreObjects.toStringHelper;

public class PublishRequest extends SessionRequest {
    private final long eventIndex;
    private final long previousIndex;
    private final List<PrimitiveEvent> events;
    
    public PublishRequest(long session, long eventIndex, long previousIndex, List<PrimitiveEvent> events) {
        super(session);
        this.eventIndex = eventIndex;
        this.previousIndex = previousIndex;
        this.events = events;
    }

    /**
     * Returns the event index.
     *
     * @return The event index.
     */
    public long eventIndex() {
        return eventIndex;
    }

    /**
     * Returns the previous event index.
     *
     * @return The previous event index.
     */
    public long previousIndex() {
        return previousIndex;
    }

    /**
     * Returns the request events.
     *
     * @return The request events.
     */
    public List<PrimitiveEvent> events() {
        return events;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), session, eventIndex, previousIndex, events, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof PublishRequest) {
            PublishRequest request = (PublishRequest) object;
            return request.session == session
                    && request.eventIndex == eventIndex
                    && request.previousIndex == previousIndex
                    && request.events.equals(events);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("session", session)
                .add("eventIndex", eventIndex)
                .add("previousIndex", previousIndex)
                .add("events", events)
                .toString();
    }
}
