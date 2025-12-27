package org.example;

public class TaskRunner implements Runnable {
    private final int id;

    public TaskRunner(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.printf("Task #%d running on %s%n", id, threadName);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}