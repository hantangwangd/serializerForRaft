package com.serializer.raft;

import java.util.UUID;

public class MemberId extends NodeId {
	/**
	 * Creates a new cluster node identifier from the specified string.
	 *
	 * @return node id
	 */
	public static MemberId anonymous() {
		return new MemberId(UUID.randomUUID().toString());
	}

	/**
	 * Creates a new cluster node identifier from the specified string.
	 *
	 * @param id
	 *            string identifier
	 * @return node id
	 */
	public static MemberId from(String id) {
		return new MemberId(id);
	}

	public MemberId(String id) {
		super(id);
	}
}
