package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> ids = Arrays.asList("101", "102", "err", "103", "104");

        long start = System.currentTimeMillis();
        List<FinalResult> results = new AsyncAggregator().fetchAllDataAsync(ids);
        long duration = System.currentTimeMillis() - start;

        System.out.println("\nРезультаты:");
        results.forEach(System.out::println);
        System.out.println("Время выполнения: " + duration + " мс");
    }
}