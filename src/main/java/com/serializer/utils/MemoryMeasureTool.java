package com.serializer.utils;

import com.memorymeasure.MemoryMeasurer;

public class MemoryMeasureTool {
	public static long measure(Object obj) {
		return MemoryMeasurer.measureBytes(obj);
	}
}
