package org.example;

public class Main {
    public static void main(String[] args) {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);

        for (int i = 0; i < 3; i++) {
            new Producer(buffer).start();
            new Consumer(buffer).start();
        }
    }
}