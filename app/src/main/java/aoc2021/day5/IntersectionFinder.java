package aoc2021.day5;

import java.util.*;

public class IntersectionFinder {
    public Map<Point, Integer> findIntersections(List<Line> lines) {
        var intersections = new HashMap<Point, Integer>();

        var intersectionsToVisit = new ArrayList<Map.Entry<Integer, Integer>>();

        for (var i = 0; i < lines.size(); i++)
            for (var j = 0; j < i; j++)
                intersectionsToVisit.add(Map.entry(i, j));

        return intersectionsToVisit.parallelStream()
                .map(point -> getIntersections(lines.get(point.getKey()), lines.get(point.getValue())))
                .reduce(new HashMap<>(), this::merge);
    }

    private Map<Point, Integer> merge(Map<Point, Integer> part1, Map<Point, Integer> part2) {
        var result = new HashMap<>(part1);

        for (var entry : part2.entrySet())
            result.merge(entry.getKey(), entry.getValue(), Integer::sum);

        return result;
    }

    private Map<Point, Integer> getIntersections(Line line1, Line line2) {
        var intersections = new HashMap<Point, Integer>();
        var commonPoints = new HashSet<>(line1.getCoveredPoints());
        commonPoints.retainAll(line2.getCoveredPoints());
        for (var commonPoint : commonPoints) {
            intersections.put(commonPoint, 1);
        }
        return intersections;
    }
}
