package org.example;

import java.util.*;
import java.util.concurrent.*;

public class PeriodicDataAggregator {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final ExecutorService fetchPool = Executors.newFixedThreadPool(4);
    private final List<String> entities = Arrays.asList("USD", "EUR", "JPY", "GBP", "CNY");

    public void start(int intervalSeconds) {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("🔄 Агрегация данных...");
            List<Callable<String>> tasks = new ArrayList<>();

            for (String entity : entities) {
                tasks.add(() -> fetchData(entity));
            }

            try {
                List<Future<String>> results = fetchPool.invokeAll(tasks);
                for (Future<String> f : results) {
                    try {
                        System.out.println("✅ " + f.get());
                    } catch (Exception e) {
                        System.out.println("❌ Ошибка: " + e.getMessage());
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, 0, intervalSeconds, TimeUnit.SECONDS);
    }

    private String fetchData(String entity) throws Exception {
        if (new Random().nextInt(5) == 0) throw new Exception("Сбой запроса для " + entity);
        Thread.sleep(new Random().nextInt(1000) + 500);
        return "Данные для " + entity + ": OK";
    }
}