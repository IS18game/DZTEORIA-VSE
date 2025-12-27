package threadstates;

public class WorkerA implements Runnable {
    @Override
    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}