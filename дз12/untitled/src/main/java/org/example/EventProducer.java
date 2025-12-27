package org.example;

import java.util.Random;

public class EventProducer implements Runnable {
    private final EventLogger logger;
    private final String source;
    private final Random random = new Random();

    public EventProducer(EventLogger logger, String source) {
        this.logger = logger;
        this.source = source;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            logger.log(new Event(source, "Событие #" + i));
            try {
                Thread.sleep(random.nextInt(300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}