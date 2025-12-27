package threadstates;

public class StatePrinter {
    public static void print(Thread... threads) {
        for (Thread t : threads) {
            System.out.println(t.getName() + " → " + t.getState());
        }
        System.out.println("-----");
    }
}