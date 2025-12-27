package org.example;

public class PlatformThreadDemo {
    public static void run(int taskCount) throws InterruptedException {
        Thread[] threads = new Thread[taskCount];
        long start = System.nanoTime();

        for (int i = 0; i < taskCount; i++) {
            int id = i;
            threads[i] = new Thread(new TaskRunner(id));
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        long duration = System.nanoTime() - start;
        System.out.printf("⏱️ Платформенные потоки: %d задач выполнено за %.2f мс%n",
                taskCount, duration / 1_000_000.0);
    }
}