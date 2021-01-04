package com.serializer.raft.request;

import com.serializer.raft.RaftMember;

public class JoinRequest extends ConfigurationRequest {
    public JoinRequest(RaftMember member) {
        super(member);
    }
}
