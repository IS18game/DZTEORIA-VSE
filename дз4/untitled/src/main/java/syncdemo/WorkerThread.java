package syncdemo;

public class WorkerThread extends Thread {
    private final DataCollector collector;
    private final Item item;

    public WorkerThread(DataCollector collector, Item item) {
        this.collector = collector;
        this.item = item;
    }

    @Override
    public void run() {
        collector.collectItem(item);
    }
}