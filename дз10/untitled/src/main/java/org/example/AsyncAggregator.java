package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class AsyncAggregator {
    private final ExecutorService executor;

    public AsyncAggregator() {
        this.executor = Executors.newFixedThreadPool(4, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("async-worker-" + t.getId());
            return t;
        });
    }

    public List<FinalResult> fetchAllDataAsync(List<String> ids) {
        List<CompletableFuture<FinalResult>> futures = new ArrayList<>();

        for (String id : ids) {
            CompletableFuture<String> nameFuture = DataService.fetchName(id, executor)
                    .thenApplyAsync(name -> {
                        log("Обработка имени: " + name, id);
                        return name.toUpperCase();
                    }, executor)
                    .exceptionally(ex -> {
                        log("Ошибка имени: " + ex.getMessage(), id);
                        return "UNKNOWN";
                    });

            CompletableFuture<Double> priceFuture = DataService.fetchPrice(id, executor)
                    .thenApplyAsync(price -> {
                        log("Обработка цены: " + price, id);
                        return Math.round(price * 100.0) / 100.0;
                    }, executor);

            CompletableFuture<FinalResult> combined = nameFuture.thenCombine(priceFuture,
                            (name, price) -> new FinalResult(id, name, price))
                    .handle((result, ex) -> {
                        if (ex != null) {
                            log("Ошибка объединения: " + ex.getMessage(), id);
                            return null;
                        }
                        return result;
                    });

            futures.add(combined);
        }

        List<FinalResult> results = futures.stream()
                .map(CompletableFuture::join)
                .filter(r -> r != null)
                .collect(Collectors.toList());

        shutdown();
        return results;
    }

    private void log(String msg, String id) {
        System.out.printf("[%s] [ID=%s] %s%n", Thread.currentThread().getName(), id, msg);
    }

    private void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}