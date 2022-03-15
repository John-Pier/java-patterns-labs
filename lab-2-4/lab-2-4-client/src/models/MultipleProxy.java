package models;

public class MultipleProxy implements Multiple {
    private final Multiple remoteMultiple = new RemoteMultiple();
    private final SimpleTupleCache cache = new SimpleTupleCache();

    public double multiple(double a, double b) {
        if (cache.contains(a, b)) {
            return cache.get(a, b);
        } else {
            var result = remoteMultiple.multiple(a, b);
            cache.add(a, b, result);
            return result;
        }
    }
}