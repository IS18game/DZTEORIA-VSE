package org.example;

public class FinalResult {
    public final String id;
    public final String name;
    public final Double price;

    public FinalResult(String id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "FinalResult{id='%s', name='%s', price=%.2f}".formatted(id, name, price);
    }
}