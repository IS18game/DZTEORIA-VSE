package threadstates;

public class WorkerB implements Runnable {
    private final Thread threadToJoin;

    public WorkerB(Thread threadToJoin) {
        this.threadToJoin = threadToJoin;
    }

    @Override
    public void run() {
        try {
            threadToJoin.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}