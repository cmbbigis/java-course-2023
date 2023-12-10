package edu.hw9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatsCollector {
    private final ConcurrentHashMap<String, List<Double>> metrics = new ConcurrentHashMap<>();

    public void push(String metricName, double[] values) {
        metrics.compute(metricName, (key, value) -> {
            List<Double> newValue = new ArrayList<>();
            for (double v : values) {
                newValue.add(v);
            }
            return newValue;
        });
    }

    public Map<String, Map<String, Double>> stats() {
        Map<String, Map<String, Double>> stats = new HashMap<>();
        metrics.forEach((key, value) -> {
            Map<String, Double> stat = new HashMap<>();
            synchronized (value) {
                stat.put("sum", value.stream().mapToDouble(Double::doubleValue).sum());
                stat.put("avg", value.stream().mapToDouble(Double::doubleValue).average().orElse(0.0));
                stat.put("max", value.stream().mapToDouble(Double::doubleValue).max().orElse(Double.MIN_VALUE));
                stat.put("min", value.stream().mapToDouble(Double::doubleValue).min().orElse(Double.MAX_VALUE));
            }
            stats.put(key, stat);
        });
        return stats;
    }
}
