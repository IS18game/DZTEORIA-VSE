package streamperf;

import java.util.*;
import java.util.stream.Collectors;

public class StreamVsParallelStream {
    public static void main(String[] args) {
        List<Integer> numbers = new Random()
                .ints(1_000_000, 1, 1001)
                .boxed()
                .collect(Collectors.toList());

        long startSequential = System.currentTimeMillis();
        long sumSequential = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .mapToLong(Integer::longValue)
                .sum();
        long timeSequential = System.currentTimeMillis() - startSequential;

        long startParallel = System.currentTimeMillis();
        long sumParallel = numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .mapToLong(Integer::longValue)
                .sum();
        long timeParallel = System.currentTimeMillis() - startParallel;

        System.out.println("Stream: сумма = " + sumSequential + ", время = " + timeSequential + " мс");
        System.out.println("ParallelStream: сумма = " + sumParallel + ", время = " + timeParallel + " мс");
    }
}