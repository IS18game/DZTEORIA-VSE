package org.example;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class UrlProducer implements Runnable {
    private final BlockingQueue<UrlTask> queue;
    private final String producerName;
    private final Random random = new Random();

    public UrlProducer(BlockingQueue<UrlTask> queue, String producerName) {
        this.queue = queue;
        this.producerName = producerName;
    }

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String url = "https://example.com/page/" + count++;
                UrlTask task = new UrlTask(url);
                queue.put(task);
                System.out.printf("[Producer-%s] Добавил задачу: %s%n", producerName, url);
                Thread.sleep(random.nextInt(300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}