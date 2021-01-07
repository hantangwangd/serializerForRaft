package com.serializer.raft.response;

import java.util.Arrays;
import java.util.Objects;

import com.serializer.raft.RaftError;
import com.serializer.utils.ArraySizeHashPrinter;

import static com.google.common.base.MoreObjects.toStringHelper;

public class OperationResponse extends SessionResponse {
    protected final long index;
    protected final long eventIndex;
    protected final byte[] result;
    protected final long lastSequence;

    public OperationResponse(Status status, RaftError error) {
        super(status, error);
        this.index = 0;
        this.eventIndex = 0;
        this.result = null;
        this.lastSequence = 0;
    }

    public OperationResponse(Status status, RaftError error, long index, long eventIndex, byte[] result) {
        super(status, error);
        this.index = index;
        this.eventIndex = eventIndex;
        this.result = result;
        this.lastSequence = 0;
    }

    public OperationResponse(Status status, RaftError error, long index, long eventIndex, byte[] result, long lastSequence) {
        super(status, error);
        this.index = index;
        this.eventIndex = eventIndex;
        this.result = result;
        this.lastSequence = lastSequence;
    }

    /**
     * Returns the operation index.
     *
     * @return The operation index.
     */
    public long index() {
        return index;
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
     * Returns the operation result.
     *
     * @return The operation result.
     */
    public byte[] result() {
        return result;
    }

    /**
     * Returns the last in sequence command.
     * <p>
     * This argument is only populated if the command request failed.
     *
     * @return The last command sequence number.
     */
    public long lastSequenceNumber() {
        return lastSequence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), status, index, eventIndex, lastSequence, result, ranId);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !getClass().isAssignableFrom(object.getClass())) return false;

        OperationResponse response = (OperationResponse) object;
        return response.status == status
                && Objects.equals(response.error, error)
                && response.index == index
                && response.eventIndex == eventIndex
                && response.lastSequence == lastSequence
                && Arrays.equals(response.result, result);
    }

    @Override
    public String toString() {
        if (status == Status.OK) {
            return toStringHelper(this)
                    .add("status", status)
                    .add("index", index)
                    .add("eventIndex", eventIndex)
                    .add("result", result != null ? ArraySizeHashPrinter.of(result) : null)
                    .toString();
        } else {
            return toStringHelper(this)
                    .add("status", status)
                    .add("error", error)
                    .add("lastSequence", lastSequence)
                    .toString();
        }
    }
}