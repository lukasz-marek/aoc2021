package aoc2021.day7;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Optimizer {
    public int findCheapestPositionCost(List<Integer> positions) {
        var minPosition = positions.stream().min(Comparator.naturalOrder()).get();
        var maxPosition = positions.stream().max(Comparator.naturalOrder()).get();
        return IntStream.rangeClosed(minPosition, maxPosition)
                .parallel()
                .boxed()
                .map(position -> Map.entry(position, computeCost(position, positions)))
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getValue)
                .get();
    }

    private int computeCost(int positionToTake, List<Integer> positions) {
        return positions.stream()
                .mapToInt(Integer::intValue)
                .map(position -> Math.abs(position - positionToTake))
                .sum();
    }
}
