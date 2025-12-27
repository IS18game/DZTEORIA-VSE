package com.example.threaddemo.threads;

public class LoggerTask implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " — лог: " + i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}