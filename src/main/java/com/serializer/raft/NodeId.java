package com.serializer.raft;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class NodeId extends AbstractIdentifier<String> implements
	Comparable<NodeId>, Serializable {
	
	/**
	 * Creates a new cluster node identifier from the specified string.
	 *
	 * @return node id
	 */
	public static NodeId anonymous() {
		return new NodeId(UUID.randomUUID().toString());
	}

	/**
	 * Creates a new cluster node identifier from the specified string.
	 *
	 * @param id
	 *            string identifier
	 * @return node id
	 */
	public static NodeId from(String id) {
		return new NodeId(id);
	}

	/**
	 * Creates a new cluster node identifier from the specified string.
	 *
	 * @param id
	 *            string identifier
	 */
	public NodeId(String id) {
		super(id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id());
	}

	@Override
	public boolean equals(Object object) {
		return object instanceof NodeId && ((NodeId) object).id().equals(id());
	}

	@Override
	public int compareTo(NodeId that) {
		return identifier.compareTo(that.identifier);
	}

}
