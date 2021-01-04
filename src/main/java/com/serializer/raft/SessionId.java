package com.serializer.raft;

public class SessionId extends AbstractIdentifier<Long> {

	  /**
	   * Returns a new session ID from the given identifier.
	   *
	   * @param id the identifier from which to create a session ID
	   * @return a new session identifier
	   */
	  public static SessionId from(long id) {
	    return new SessionId(id);
	  }

	  public SessionId(Long value) {
	    super(value);
	  }
	}