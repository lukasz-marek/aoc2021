package aoc2021.day7;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class Optimizer {
    public long findCheapestPositionCost(List<Integer> positions, IntFunction<Long> costFunction) {
        var minPosition = positions.stream().min(Comparator.naturalOrder()).get();
        var maxPosition = positions.stream().max(Comparator.naturalOrder()).get();
        return IntStream.rangeClosed(minPosition, maxPosition)
                .parallel()
                .boxed()
                .map(position -> Map.entry(position, computeCost(position, positions, costFunction)))
                .min(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getValue)
                .get();
    }

    private long computeCost(int positionToTake, List<Integer> positions, IntFunction<Long> costFunction) {
        return positions.stream()
                .mapToInt(Integer::intValue)
                .mapToLong(position -> costFunction.apply(Math.abs(position - positionToTake)))
                .sum();
    }
}
