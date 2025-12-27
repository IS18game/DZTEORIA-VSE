package org.example;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicCache {
    private final AtomicReference<String> cache = new AtomicReference<>();

    public String getOrCreate() {
        String value = cache.get();
        if (value == null) {
            String newValue = "cached@" + System.nanoTime();
            if (cache.compareAndSet(null, newValue)) {
                return newValue;
            } else {
                return cache.get();
            }
        }
        return value;
    }
}