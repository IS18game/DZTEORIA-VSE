package org.example;

import java.util.concurrent.BlockingQueue;

public class UrlConsumer implements Runnable {
    private final BlockingQueue<UrlTask> queue;
    private final String consumerName;

    public UrlConsumer(BlockingQueue<UrlTask> queue, String consumerName) {
        this.queue = queue;
        this.consumerName = consumerName;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                UrlTask task = queue.take();
                System.out.printf("[Consumer-%s] Обрабатываю: %s%n", consumerName, task.getUrl());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}