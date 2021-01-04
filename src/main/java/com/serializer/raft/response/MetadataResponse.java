package com.serializer.raft.response;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.serializer.raft.RaftError;
import com.serializer.raft.SessionMetadata;

import static com.google.common.base.MoreObjects.toStringHelper;

public class MetadataResponse extends AbstractRaftResponse {
    private final HashSet<SessionMetadata> sessions;

    public MetadataResponse(Status status, RaftError error) {
        super(status, error);
        this.sessions = null;
    }

    public MetadataResponse(Status status, RaftError error, Set<SessionMetadata> sessions) {
        super(status, error);
        if (sessions != null) {
            this.sessions = new HashSet<>(sessions);
        } else {
            this.sessions = null;
        }
    }

    /**
     * Returns the session metadata.
     *
     * @return Session metadata.
     */
    public Set<SessionMetadata> sessions() {
        return sessions;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof MetadataResponse) {
        	MetadataResponse response = (MetadataResponse) object;
            return response.status == status
            		&& Objects.equals(response.error, error)
            		&& Objects.equals(response.sessions, sessions);
            }
        return false;
    }
    
    @Override
    public String toString() {
        if (status == Status.OK) {
            return toStringHelper(this)
                    .add("status", status)
                    .add("sessions", sessions)
                    .toString();
        } else {
            return toStringHelper(this)
                    .add("status", status)
                    .add("error", error)
                    .toString();
        }
    }
}