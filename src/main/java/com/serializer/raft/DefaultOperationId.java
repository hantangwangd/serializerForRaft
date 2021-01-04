package com.serializer.raft;

import static com.google.common.base.MoreObjects.toStringHelper;

public class DefaultOperationId extends AbstractIdentifier<String> implements OperationId {
	private final OperationType type;

	public DefaultOperationId(String id, OperationType type) {
		super(id);
		this.type = type;
	}

	/**
	 * Returns the operation type.
	 *
	 * @return the operation type
	 */
	public OperationType type() {
		return type;
	}

	@Override
	public String toString() {
		return toStringHelper(this).add("id", id()).add("type", type()).toString();
	}
}
