package org.example;

public class StarvationExample {
    public void run() {
        Object lock = new Object();

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> {
                while (true) {
                    synchronized (lock) {
                        System.out.println(Thread.currentThread().getName() + " работает");
                        try { Thread.sleep(50); } catch (InterruptedException ignored) {}
                    }
                }
            }, "HighPriority-" + i);
            t.setPriority(Thread.MAX_PRIORITY);
            t.start();
        }

        Thread low = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    System.out.println("LowPriority работает (если повезёт)");
                    try { Thread.sleep(50); } catch (InterruptedException ignored) {}
                }
            }
        }, "LowPriority");
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
    }
}