package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        List<AggregatedElement> elements = new ArrayList<>();
        for (int i = 0; i < 37; i++) {
            elements.add(new AggregatedElement("item-" + i));
        }

        AggregationTask task = new AggregationTask(elements, 0, elements.size());
        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(task);

        System.out.println("Общее количество агрегированных элементов: " + result);
    }
}