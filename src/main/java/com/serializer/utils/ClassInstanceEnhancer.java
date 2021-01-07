package com.serializer.utils;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;
import sun.reflect.*;

public class ClassInstanceEnhancer {

	@SuppressWarnings("restriction")
	private final ReflectionFactory REFLECTION_FACTORY = ReflectionFactory.getReflectionFactory();

	private final ConcurrentMap<Class<?>, Constructor<?>> _constructors =
			Maps.newConcurrentMap();

	public <T> T newInstance(Class<T> type) {
		try {
			Constructor<?> constructor = _constructors.get(type);
			if (constructor != null) {
				return (T)newInstanceFrom(constructor);
			} else {
				return type.newInstance();
			}
		} catch (Exception e) {
			return (T) newInstanceFromReflectionFactory(type);
		}
	}

	private Object newInstanceFrom(Constructor<?> constructor) {
		try {
			return constructor.newInstance();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T newInstanceFromReflectionFactory(Class<T> type) {
		Constructor<?> constructor = _constructors.computeIfAbsent(type, k -> newConstructorForSerialization(k));
		return (T) newInstanceFrom(constructor);
	}

	@SuppressWarnings("restriction")
	private <T> Constructor<?> newConstructorForSerialization(
			Class<T> type) {
		try {
			Constructor<?> constructor = REFLECTION_FACTORY
					.newConstructorForSerialization(type,
							Object.class.getDeclaredConstructor());
			constructor.setAccessible(true);
			return constructor;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
