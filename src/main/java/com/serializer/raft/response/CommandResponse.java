package com.serializer.raft.response;

import com.serializer.raft.RaftError;

public class CommandResponse extends OperationResponse {

    public CommandResponse(Status status, RaftError error) {
        super(status, error);
    }

    public CommandResponse(Status status, RaftError error, long index, long eventIndex, byte[] result) {
        super(status, error, index, eventIndex, result);
    }

    public CommandResponse(Status status, RaftError error, long index, long eventIndex, byte[] result, long lastSequence) {
        super(status, error, index, eventIndex, result, lastSequence);
    }
}