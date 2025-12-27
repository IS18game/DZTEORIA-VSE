package threadstates;

public class WorkerC implements Runnable {
    @Override
    public void run() {
        synchronized (WorkerA.class) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}