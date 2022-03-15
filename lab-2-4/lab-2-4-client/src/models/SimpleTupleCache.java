package models;

import java.util.*;

public class SimpleTupleCache {
    private final List<Double[]> cached = new LinkedList<>();

    public void add(double a, double b, double result) {
        cached.add(new Double[]{a, b, result});
    }

    public boolean contains(double a, double b) {
        return cached.stream().anyMatch(doubles -> doubles[0] == a && doubles[1] == b);
    }

    public double get(double a, double b) {
        var result = cached.stream().filter(doubles -> doubles[0] == a && doubles[1] == b).findFirst().orElse(null);
        return result != null ? result[2] : Double.NaN;
    }
}
