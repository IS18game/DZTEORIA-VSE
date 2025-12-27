package org.example;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<UrlTask> queue = new ArrayBlockingQueue<>(10); // ограниченный размер

        ExecutorService executor = Executors.newCachedThreadPool();

        executor.submit(new UrlProducer(queue, "A"));
        executor.submit(new UrlProducer(queue, "B"));
        executor.submit(new UrlConsumer(queue, "X"));
        executor.submit(new UrlConsumer(queue, "Y"));

        Thread.sleep(5000);
        executor.shutdownNow();
    }
}