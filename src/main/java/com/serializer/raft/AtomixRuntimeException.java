package com.serializer.raft;

public class AtomixRuntimeException extends RuntimeException {
	  public AtomixRuntimeException() {
	  }

	  public AtomixRuntimeException(String message) {
	    super(message);
	  }

	  public AtomixRuntimeException(String message, Object... args) {
	    super(String.format(message, args));
	  }

	  public AtomixRuntimeException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public AtomixRuntimeException(Throwable cause) {
	    super(cause);
	  }
}
