package org.example;

import java.util.concurrent.atomic.AtomicBoolean;

public class LivelockExample {
    private final AtomicBoolean switchFlag = new AtomicBoolean(true);

    public void run() {
        class Worker extends Thread {
            private final String name;

            Worker(String name) {
                this.name = name;
            }

            public void run() {
                while (true) {
                    if (switchFlag.get() && name.equals("A")) {
                        System.out.println("Worker A: yielding to B");
                        switchFlag.set(false);
                    } else if (!switchFlag.get() && name.equals("B")) {
                        System.out.println("Worker B: yielding to A");
                        switchFlag.set(true);
                    }
                }
            }
        }

        new Worker("A").start();
        new Worker("B").start();
    }
}