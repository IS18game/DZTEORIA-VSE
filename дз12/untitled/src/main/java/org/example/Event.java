package org.example;

public class Event {
    private final String source;
    private final String message;
    private final long timestamp;

    public Event(String source, String message) {
        this.source = source;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "[%d] [%s] %s".formatted(timestamp, source, message);
    }
}