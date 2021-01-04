package com.serializer.raft;

public class DefaultEventType extends AbstractIdentifier<String> implements EventType {
	  public DefaultEventType(String value) {
	    super(value);
	  }
	}
