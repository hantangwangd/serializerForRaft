package com.serializer.raft.creator;

import com.serializer.raft.request.RaftRequest;

public interface RaftRequestCreator {
	public RaftRequest create();
}
