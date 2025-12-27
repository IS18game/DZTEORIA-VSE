package org.example;

import java.util.*;
import java.util.concurrent.*;

public class BatchTaskDemo {
    public void run() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int id = i;
            tasks.add(() -> {
                int delay = new Random().nextInt(2000) + 1000;
                Thread.sleep(delay);
                System.out.println("Задача " + id + " завершена за " + delay + " мс");
                return delay;
            });
        }

        List<Future<Integer>> results = executor.invokeAll(tasks);
        for (Future<Integer> f : results) {
            try {
                System.out.println("Результат: " + f.get());
            } catch (ExecutionException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        executor.shutdown();
    }
}