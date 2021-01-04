package com.serializer.raft.request;

import java.util.Arrays;
import java.util.Objects;

import com.serializer.raft.MemberId;
import com.serializer.utils.ArraySizeHashPrinter;

import static com.google.common.base.MoreObjects.toStringHelper;

public class InstallRequest extends AbstractRaftRequest {

    private final long term;
    private final MemberId leader;
    private final long index;
    private final long timestamp;
    private final int version;
    private final int offset;
    private final byte[] data;
    private final boolean complete;
    
    public InstallRequest(long term, MemberId leader, long index, long timestamp, int version, int offset, byte[] data, boolean complete) {
        this.term = term;
        this.leader = leader;
        this.index = index;
        this.timestamp = timestamp;
        this.version = version;
        this.offset = offset;
        this.data = data;
        this.complete = complete;
    }

    /**
     * Returns the requesting node's current term.
     *
     * @return The requesting node's current term.
     */
    public long term() {
        return term;
    }

    /**
     * Returns the requesting leader address.
     *
     * @return The leader's address.
     */
    public MemberId leader() {
        return leader;
    }

    /**
     * Returns the snapshot index.
     *
     * @return The snapshot index.
     */
    public long snapshotIndex() {
        return index;
    }

    /**
     * Returns the snapshot timestamp.
     *
     * @return The snapshot timestamp.
     */
    public long snapshotTimestamp() {
        return timestamp;
    }

    /**
     * Returns the snapshot version.
     *
     * @return the snapshot version
     */
    public int snapshotVersion() {
        return version;
    }

    /**
     * Returns the offset of the snapshot chunk.
     *
     * @return The offset of the snapshot chunk.
     */
    public int chunkOffset() {
        return offset;
    }

    /**
     * Returns the snapshot data.
     *
     * @return The snapshot data.
     */
    public byte[] data() {
        return data;
    }

    /**
     * Returns a boolean value indicating whether this is the last chunk of the snapshot.
     *
     * @return Indicates whether this request is the last chunk of the snapshot.
     */
    public boolean complete() {
        return complete;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, leader, index, offset, complete, data);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof InstallRequest) {
            InstallRequest request = (InstallRequest) object;
            return request.term == term
                    && request.leader.equals(leader)
                    && request.index == index
                    && request.offset == offset
                    && request.complete == complete
                    && Arrays.equals(request.data, data);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .add("leader", leader)
                .add("index", index)
                .add("timestamp", timestamp)
                .add("version", version)
                .add("offset", offset)
                .add("data", ArraySizeHashPrinter.of(data))
                .add("complete", complete)
                .toString();
    }
}