package com.serializer.raft.response;

import com.serializer.raft.RaftError;

public class SessionResponse extends AbstractRaftResponse {
    protected SessionResponse(Status status, RaftError error) {
        super(status, error);
    }
}