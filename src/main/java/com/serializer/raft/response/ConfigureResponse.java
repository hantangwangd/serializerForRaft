package com.serializer.raft.response;

import com.serializer.raft.RaftError;

public class ConfigureResponse extends AbstractRaftResponse {

    public ConfigureResponse(Status status, RaftError error) {
        super(status, error);
    }
}
