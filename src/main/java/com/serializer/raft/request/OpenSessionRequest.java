package com.serializer.raft.request;

import java.util.Objects;

import com.serializer.raft.ReadConsistency;

import static com.google.common.base.MoreObjects.toStringHelper;

public class OpenSessionRequest extends AbstractRaftRequest {

    private final String node;
    private final String name;
    private final String typeName;
    private final byte[] config;
    private final ReadConsistency readConsistency;
    private final long minTimeout;
    private final long maxTimeout;
    
    public OpenSessionRequest(String node, String name, String typeName, byte[] config, ReadConsistency readConsistency, long minTimeout, long maxTimeout) {
        this.node = node;
        this.name = name;
        this.typeName = typeName;
        this.config = config;
        this.readConsistency = readConsistency;
        this.minTimeout = minTimeout;
        this.maxTimeout = maxTimeout;
    }

    /**
     * Returns the client node identifier.
     *
     * @return The client node identifier.
     */
    public String node() {
        return node;
    }

    /**
     * Returns the state machine name.
     *
     * @return The state machine name.
     */
    public String serviceName() {
        return name;
    }

    /**
     * Returns the state machine type;
     *
     * @return The state machine type.
     */
    public String serviceType() {
        return typeName;
    }

    /**
     * Returns the service configuration.
     *
     * @return the service configuration
     */
    public byte[] serviceConfig() {
        return config;
    }

    /**
     * Returns the session read consistency level.
     *
     * @return The session's read consistency.
     */
    public ReadConsistency readConsistency() {
        return readConsistency;
    }

    /**
     * Returns the minimum session timeout.
     *
     * @return The minimum session timeout.
     */
    public long minTimeout() {
        return minTimeout;
    }

    /**
     * Returns the maximum session timeout.
     *
     * @return The maximum session timeout.
     */
    public long maxTimeout() {
        return maxTimeout;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), name, typeName, minTimeout, maxTimeout);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof OpenSessionRequest) {
            OpenSessionRequest request = (OpenSessionRequest) object;
            return request.node.equals(node)
                    && request.name.equals(name)
                    && request.typeName.equals(typeName)
                    && request.readConsistency == readConsistency
                    && request.minTimeout == minTimeout
                    && request.maxTimeout == maxTimeout;
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("node", node)
                .add("serviceName", name)
                .add("serviceType", typeName)
                .add("readConsistency", readConsistency)
                .add("minTimeout", minTimeout)
                .add("maxTimeout", maxTimeout)
                .toString();
    }
}
