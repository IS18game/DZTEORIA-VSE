package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventLogger {
    private final BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();

    public void log(Event event) {
        eventQueue.offer(event);
    }

    public Event take() throws InterruptedException {
        return eventQueue.take();
    }

    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }
}