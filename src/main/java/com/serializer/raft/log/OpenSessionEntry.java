package com.serializer.raft.log;

import com.serializer.raft.ReadConsistency;
import com.serializer.utils.ArraySizeHashPrinter;
import com.serializer.utils.TimestampPrinter;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Arrays;
import java.util.Objects;

public class OpenSessionEntry extends TimestampedEntry {
    private final String memberId;
    private final String serviceName;
    private final String serviceType;
    private final byte[] serviceConfig;
    private final ReadConsistency readConsistency;
    private final long minTimeout;
    private final long maxTimeout;

    public OpenSessionEntry() {
    	super(-1, -1);
    	this.memberId = null;
    	this.serviceName = null;
    	this.serviceType = null;
    	this.serviceConfig = null;
    	this.readConsistency = null;
    	this.minTimeout = -1;
    	this.maxTimeout = -1;
    }
    
    public OpenSessionEntry(
            long term,
            long timestamp,
            String memberId,
            String serviceName,
            String serviceType,
            byte[] serviceConfig,
            ReadConsistency readConsistency,
            long minTimeout,
            long maxTimeout) {
        super(term, timestamp);
        this.memberId = memberId;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.serviceConfig = serviceConfig;
        this.readConsistency = readConsistency;
        this.minTimeout = minTimeout;
        this.maxTimeout = maxTimeout;
    }

    /**
     * Returns the client node identifier.
     *
     * @return The client node identifier.
     */
    public String memberId() {
        return memberId;
    }

    /**
     * Returns the session state machine name.
     *
     * @return The session's state machine name.
     */
    public String serviceName() {
        return serviceName;
    }

    /**
     * Returns the session state machine type name.
     *
     * @return The session's state machine type name.
     */
    public String serviceType() {
        return serviceType;
    }

    /**
     * Returns the service configuration.
     *
     * @return the service configuration
     */
    public byte[] serviceConfig() {
        return serviceConfig;
    }

    /**
     * Returns the session read consistency level.
     *
     * @return The session's read consistency level.
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
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .add("timestamp", new TimestampPrinter(timestamp))
                .add("node", memberId)
                .add("serviceName", serviceName)
                .add("serviceType", serviceType)
                .add("serviceConfig", ArraySizeHashPrinter.of(serviceConfig))
                .add("readConsistency", readConsistency)
                .add("minTimeout", minTimeout)
                .add("maxTimeout", maxTimeout)
                .toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), term, timestamp, memberId, serviceName, serviceType,
        		serviceConfig, readConsistency, minTimeout, maxTimeout);
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof OpenSessionEntry) {
    		OpenSessionEntry entry = (OpenSessionEntry) object;
    		return entry.term == term
                    && entry.timestamp == timestamp
    				&& entry.memberId.equals(memberId)
    				&& entry.serviceName.equals(serviceName)
    				&& entry.serviceType.equals(serviceType)
    				&& Arrays.equals(entry.serviceConfig, serviceConfig)
    				&& entry.readConsistency.equals(readConsistency)
    				&& entry.minTimeout == minTimeout
    				&& entry.maxTimeout == maxTimeout;
    	}
    	return false;
    }
}
