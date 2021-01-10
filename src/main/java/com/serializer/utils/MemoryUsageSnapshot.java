package com.serializer.utils;

import static com.google.common.base.MoreObjects.toStringHelper;

public class MemoryUsageSnapshot {
    private final long initMemorySize;
    private final long maxMemorySize;
    private final long usedMemorySize;
    //已分配内存大小
    private final long committedMemorySize;

    public MemoryUsageSnapshot(long initMemorySize, long maxMemorySize,
                               long usedMemorySize, long committedMemorySize) {
        this.initMemorySize = initMemorySize;
        this.maxMemorySize = maxMemorySize;
        this.usedMemorySize = usedMemorySize;
        this.committedMemorySize = committedMemorySize;
    }

    public long getInitMemorySize() {
        return this.initMemorySize;
    }

    public long getMaxMemorySize() {
        return this.maxMemorySize;
    }

    public long getUsedMemorySize() {
        return this.usedMemorySize;
    }

    public long getCommittedMemorySize() {
        return this.committedMemorySize;
    }

    public MemoryUsageSnapshot subtract(MemoryUsageSnapshot snapshot) {
        return new MemoryUsageSnapshot(this.initMemorySize - snapshot.initMemorySize,
                this.maxMemorySize - snapshot.maxMemorySize,
                this.usedMemorySize - snapshot.usedMemorySize,
                this.committedMemorySize - snapshot.committedMemorySize);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("initMemorySize", this.initMemorySize)
                .add("maxMemorySize", this.maxMemorySize)
                .add("usedMemorySize", this.usedMemorySize)
                .add("committedMemorySize", this.committedMemorySize)
                .toString();
    }
}
