package com.serializer.raft;

public interface EventType extends Identifier<String> {

	/**
	 * Creates a new Raft event identifier.
	 *
	 * @param name the event name
	 * @return the event identifier
	 */
	static EventType from(String name) {
		return new DefaultEventType(name);
	}

	/**
	 * Simplifies the given event type.
	 *
	 * @param eventType the event type to simplify
	 * @return the simplified event type
	 */
	static EventType canonical(EventType eventType) {
		return new DefaultEventType(eventType.id());
	}

	/**
	 * Returns an identical event type in canonical form.
	 *
	 * @return an identical event type in canonical form
	 */
	default EventType canonicalize() {
		return canonical(this);
	}
}