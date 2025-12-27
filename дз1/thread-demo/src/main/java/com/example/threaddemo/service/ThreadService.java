package com.example.threaddemo.service;

import com.example.threaddemo.threads.CounterWorker;
import com.example.threaddemo.threads.LoggerTask;

import java.util.Map;

public class ThreadService {
    public void runThreads() {
        CounterWorker counterWorker = new CounterWorker();
        counterWorker.setName("CounterWorker");
        counterWorker.start();

        Thread loggerThread = new Thread(new LoggerTask(), "LoggerThread");
        loggerThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n📋 Активные потоки:");
        Map<Thread, StackTraceElement[]> threadMap = Thread.getAllStackTraces();
        for (Thread t : threadMap.keySet()) {
            System.out.println("→ " + t.getName());
        }
    }
}