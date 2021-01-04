package com.serializer.raft.response;

import java.util.Collection;

import com.serializer.raft.RaftError;
import com.serializer.raft.RaftMember;

public class JoinResponse extends ConfigurationResponse {

    public JoinResponse(Status status, RaftError error) {
        super(status, error);
    }

    public JoinResponse(Status status, RaftError error, long index, long term, long timestamp, Collection<RaftMember> members) {
        super(status, error, index, term, timestamp, members);
    }
}