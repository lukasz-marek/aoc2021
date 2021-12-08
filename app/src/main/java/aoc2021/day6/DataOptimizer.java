package aoc2021.day6;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataOptimizer {
    public Map<Lanternfish, Long> optimize(List<Lanternfish> data) {
        return data.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
