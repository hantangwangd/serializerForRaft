package com.serializer.raft.response;

import com.serializer.raft.RaftError;

public class InstallResponse extends AbstractRaftResponse {

    public InstallResponse(Status status, RaftError error) {
        super(status, error);
    }
}
