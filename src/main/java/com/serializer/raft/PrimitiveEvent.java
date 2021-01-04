package com.serializer.raft;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class PrimitiveEvent implements Serializable {

	  /**
	   * Creates a new primitive event.
	   *
	   * @param eventType the event type
	   * @return the primitive event
	   */
	  public static PrimitiveEvent event(EventType eventType) {
	    return event(eventType, null);
	  }

	  /**
	   * Creates a new primitive event.
	   *
	   * @param eventType the event type
	   * @param value     the event value
	   * @return the primitive event
	   */
	  public static PrimitiveEvent event(EventType eventType, byte[] value) {
	    return new PrimitiveEvent(EventType.canonical(eventType), value);
	  }

	  private final EventType type;
	  private final byte[] value;

	  public PrimitiveEvent(EventType type, byte[] value) {
	    this.type = type;
	    this.value = value;
	  }

	  /**
	   * Returns the event type identifier.
	   *
	   * @return the event type identifier
	   */
	  public EventType type() {
	    return type;
	  }

	  /**
	   * Returns the event value.
	   *
	   * @return the event value
	   */
	  public byte[] value() {
	    return value;
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(getClass(), type, value);
	  }

	  @Override
	  public boolean equals(Object object) {
	    if (object instanceof PrimitiveEvent) {
	      PrimitiveEvent event = (PrimitiveEvent) object;
	      return Objects.equals(event.type, type) && Arrays.equals(event.value, value);
	    }
	    return false;
	  }

	  @Override
	  public String toString() {
	    return toStringHelper(this)
	            .add("type", type)
	            .add("value", value)
	            .toString();
	  }
	}
