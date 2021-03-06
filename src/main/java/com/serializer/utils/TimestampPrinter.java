package com.serializer.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimestampPrinter {
	/**
	   * Returns a new timestamp printer.
	   *
	   * @param timestamp the timestamp to print
	   * @return the timestamp printer
	   */
	  public static TimestampPrinter of(long timestamp) {
	    return new TimestampPrinter(timestamp);
	  }

	  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss,SSS");

	  private final long timestamp;

	  public TimestampPrinter(long timestamp) {
	    this.timestamp = timestamp;
	  }

	  @Override
	  public String toString() {
	    return FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()));
	  }
}
