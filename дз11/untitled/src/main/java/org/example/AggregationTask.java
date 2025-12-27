package org.example;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class AggregationTask extends RecursiveTask<Integer> {
    private static final int BATCH_SIZE = 5;

    private final List<AggregatedElement> elements;
    private final int start;
    private final int end;

    public AggregationTask(List<AggregatedElement> elements, int start, int end) {
        this.elements = elements;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int length = end - start;
        if (length <= BATCH_SIZE) {
            return processBatch();
        } else {
            int mid = start + length / 2;
            AggregationTask left = new AggregationTask(elements, start, mid);
            AggregationTask right = new AggregationTask(elements, mid, end);
            left.fork();
            int rightResult = right.compute();
            int leftResult = left.join();
            return leftResult + rightResult;
        }
    }

    private int processBatch() {
        int count = 0;
        for (int i = start; i < end; i++) {
            AggregatedElement el = elements.get(i);
            if (el.getId() != null && !el.getId().isBlank()) {
                count++;
            }
        }
        return count;
    }
}