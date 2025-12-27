package syncdemo;

import java.util.*;

public class DataCollector {
    private final Set<String> processedKeys = new HashSet<>();
    private final List<Item> items = new ArrayList<>();
    private int processedCount = 0;

    public synchronized void collectItem(Item item) {
        if (isAlreadyProcessed(item.getKey())) {
            return;
        }

        items.add(item);
        processedKeys.add(item.getKey());
        incrementProcessed();

        notifyAll();
    }

    public synchronized void incrementProcessed() {
        processedCount++;
    }

    public synchronized boolean isAlreadyProcessed(String key) {
        return processedKeys.contains(key);
    }

    public synchronized int getProcessedCount() {
        return processedCount;
    }

    public synchronized int getItemCount() {
        return items.size();
    }
}