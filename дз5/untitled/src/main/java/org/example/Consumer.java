package org.example;

public class Consumer extends Thread {
    private final BoundedBuffer<Integer> buffer;

    public Consumer(BoundedBuffer<Integer> buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                int item = buffer.take();
                System.out.println("Consumer took: " + item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}