package com.serializer.raft.response;

import com.serializer.raft.RaftError;

public class TransferResponse extends AbstractRaftResponse {

    public TransferResponse(Status status, RaftError error) {
        super(status, error);
    }
}