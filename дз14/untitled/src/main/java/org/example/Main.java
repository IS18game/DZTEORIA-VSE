package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int taskCount = 10_000;

        System.out.println("▶️ Виртуальные потоки:");
        VirtualThreadDemo.run(taskCount);

        System.out.println("\n▶️ Платформенные потоки:");
        PlatformThreadDemo.run(taskCount);
    }
}