package com.serializer.raft;

public interface Identifier<T extends Comparable<T>> {
	/**
	 * Returns the backing identifier value.
	 *
	 * @return identifier
	 */
	T id();
}
