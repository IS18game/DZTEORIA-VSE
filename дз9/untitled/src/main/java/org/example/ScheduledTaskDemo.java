package org.example;

import java.util.concurrent.*;

public class ScheduledTaskDemo {
    public void run() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Мониторинг очереди: " + System.currentTimeMillis());

        scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }
}