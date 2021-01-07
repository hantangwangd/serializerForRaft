package com.serializer.raft.request;

import java.util.Random;

public abstract class AbstractRaftRequest implements RaftRequest {
    transient Random ran = new Random();
    long ranId = ran.nextInt(10000);
    public void random() {
        ranId = ran.nextInt(10000);
    }
}
