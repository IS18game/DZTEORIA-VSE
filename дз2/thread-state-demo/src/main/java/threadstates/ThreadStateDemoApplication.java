package threadstates;

public class ThreadStateDemoApplication {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new WorkerA(), "Thread-A");
        Thread threadB = new Thread(new WorkerB(threadA), "Thread-B");
        Thread threadC = new Thread(new WorkerC(), "Thread-C");

        System.out.println("Состояние A после создания: " + threadA.getState());
        System.out.println("Состояние B после создания: " + threadB.getState());
        System.out.println("Состояние C после создания: " + threadC.getState());

        threadA.start();
        threadB.start();
        threadC.start();

        while (threadA.isAlive() ||  threadB.isAlive() ||  threadC.isAlive()) {
            StatePrinter.print(threadA, threadB, threadC);
            Thread.sleep(300);
        }

        System.out.println("Финальные состояния:");
        StatePrinter.print(threadA, threadB, threadC);
    }
}