package syncdemo;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DataCollector collector = new DataCollector();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String key = "item" + (i % 50);
            Item item = new Item(key);
            Thread t = new WorkerThread(collector, item);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Обработано уникальных элементов: " + collector.getItemCount());
        System.out.println("Всего обработано: " + collector.getProcessedCount());
    }
}