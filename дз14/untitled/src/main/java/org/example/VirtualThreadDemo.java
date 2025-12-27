package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class VirtualThreadDemo {
    public static void run(int taskCount) throws InterruptedException {
        long start = System.nanoTime();
        CountDownLatch latch = new CountDownLatch(taskCount);

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < taskCount; i++) {
            int id = i;
            executor.submit(() -> {
                try {
                    System.out.printf("Task #%d running on %s%n", id, Thread.currentThread().getName());
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        long duration = System.nanoTime() - start;
        System.out.printf("⏱️ Виртуальные потоки: %d задач выполнено за %.2f мс%n",
                taskCount, duration / 1_000_000.0);
    }
}