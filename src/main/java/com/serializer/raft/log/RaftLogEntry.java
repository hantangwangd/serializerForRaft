package com.serializer.raft.log;

import java.io.Serializable;

import static com.google.common.base.MoreObjects.toStringHelper;

public abstract class RaftLogEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1231231232L;
	protected final long term;

    public RaftLogEntry(long term) {
        this.term = term;
    }

    /**
     * Returns the entry term.
     *
     * @return The entry term.
     */
    public long term() {
        return term;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("term", term)
                .toString();
    }
}
