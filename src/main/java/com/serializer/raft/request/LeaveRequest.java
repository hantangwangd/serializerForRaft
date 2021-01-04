package com.serializer.raft.request;

import com.serializer.raft.RaftMember;

public class LeaveRequest extends ConfigurationRequest {

	public LeaveRequest(RaftMember member) {
        super(member);
    }
}
