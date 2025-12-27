package org.example;

public class StopFlagDemo extends Thread {
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Работаю...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Поток остановлен.");
    }

    public void stopRunning() {
        running = false;
    }
}