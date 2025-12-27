package org.example;

import java.util.concurrent.*;

public class FutureCancelDemo {
    public void run() throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> longTask = () -> {
            try {
                Thread.sleep(8000);
                return "Задача завершена";
            } catch (InterruptedException e) {
                return "Задача прервана";
            }
        };

        Future<String> future = executor.submit(longTask);
        Thread.sleep(3000);
        boolean cancelled = future.cancel(true);
        System.out.println("Отмена задачи: " + cancelled);

        executor.shutdown();
    }
}