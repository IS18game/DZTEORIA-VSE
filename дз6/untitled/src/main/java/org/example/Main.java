package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        StopFlagDemo thread = new StopFlagDemo();
        thread.start();
        Thread.sleep(500);
        thread.stopRunning();

        AtomicCounter counter = new AtomicCounter();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("Итоговое значение счётчика: " + counter.get());

        AtomicCache cache = new AtomicCache();
        Thread[] readers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Thread(() -> {
                System.out.println("Кэш: " + cache.getOrCreate());
            });
            readers[i].start();
        }
        for (Thread t : readers) t.join();
    }
}