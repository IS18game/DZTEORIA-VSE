package org.example;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class DataService {
    private static final Random random = new Random();

    public static CompletableFuture<String> fetchName(String id, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            if ("err".equals(id)) throw new RuntimeException("Ошибка имени для " + id);
            return "Name_" + id;
        }, executor);
    }

    public static CompletableFuture<Double> fetchPrice(String id, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            return 100 + random.nextDouble() * 50;
        }, executor);
    }

    private static void simulateDelay() {
        try {
            Thread.sleep(500 + random.nextInt(500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}