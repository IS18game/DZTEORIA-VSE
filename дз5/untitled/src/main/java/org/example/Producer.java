package org.example;

public class Producer extends Thread {
    private final BoundedBuffer<Integer> buffer;

    public Producer(BoundedBuffer<Integer> buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                buffer.put(i);
                System.out.println("Producer put: " + i);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}