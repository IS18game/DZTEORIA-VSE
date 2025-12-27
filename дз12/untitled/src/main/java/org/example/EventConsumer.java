package org.example;

public class EventConsumer implements Runnable {
    private final EventLogger logger;

    public EventConsumer(EventLogger logger) {
        this.logger = logger;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Event event = logger.take();
                System.out.println("Обработано: " + event);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}