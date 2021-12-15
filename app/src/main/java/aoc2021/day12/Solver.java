package aoc2021.day12;

import aoc2021.common.InputLoader;

import java.util.Map;
import java.util.function.BiFunction;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final PathCounter pathCounter;

    public Solver() {
        inputConverter = new InputConverter();
        inputLoader = new InputLoader();
        pathCounter = new PathCounter();
    }

    public long countPathsPart1() {
        var input = inputLoader.loadInput("day12_1.txt");
        var waypoints = inputConverter.convert(input);
        return pathCounter.countPaths(waypoints, provideVisitationPredicatePart1());
    }

    public long countPathsPart2() {
        var input = inputLoader.loadInput("day12_1.txt");
        var waypoints = inputConverter.convert(input);
        return pathCounter.countPaths(waypoints, provideVisitationPredicatePart2());
    }

    private BiFunction<String, Map<String, Integer>, Boolean> provideVisitationPredicatePart2() {
        return new BiFunction<>() {
            @Override
            public Boolean apply(String waypoint, Map<String, Integer> smallCaveVisits) {
                if (waypoint.equals("start"))
                    return false;

                return isBig(waypoint) || smallCaveVisits.getOrDefault(waypoint, 0) < 1 || noneVisitedMoreThanOnce(smallCaveVisits);
            }

            private boolean noneVisitedMoreThanOnce(Map<String, Integer> visitationCounter) {
                return visitationCounter.values().stream().noneMatch(visits -> visits > 1);
            }
        };
    }


    private BiFunction<String, Map<String, Integer>, Boolean> provideVisitationPredicatePart1() {
        return (waypoint, smallCaveVisits) -> isBig(waypoint) || smallCaveVisits.getOrDefault(waypoint, 0) < 1;
    }

    private boolean isBig(String waypoint) {
        return !waypoint.toLowerCase().equals(waypoint);
    }
}
