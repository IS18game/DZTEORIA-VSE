package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EventLogger logger = new EventLogger();
        ExecutorService executor = Executors.newCachedThreadPool();

        executor.submit(new EventConsumer(logger));
        executor.submit(new EventProducer(logger, "AuthService"));
        executor.submit(new EventProducer(logger, "PaymentService"));
        executor.submit(new EventProducer(logger, "NotificationService"));

        Thread.sleep(3000);
        executor.shutdownNow();
    }
}