package com.serializer.raft.response;

import java.util.Objects;

import com.serializer.raft.RaftError;

public class CloseSessionResponse extends SessionResponse {

    public CloseSessionResponse(Status status, RaftError error) {
        super(status, error);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CloseSessionResponse) {
            CloseSessionResponse response = (CloseSessionResponse) object;
            return response.status == status && Objects.equals(response.error, error);
        }
        return false;
    }
}