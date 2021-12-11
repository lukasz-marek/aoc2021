package aoc2021.day5;

import java.util.*;

public class IntersectionFinder {
    public Map<Point, Integer> findIntersections(List<Line> lines) {
        var intersections = new HashMap<Point, Integer>();

        var intersectionsToVisit = new ArrayList<Map.Entry<Integer, Integer>>();

        for (var i = 0; i < lines.size(); i++)
            for (var j = 0; j < i; j++)
                intersectionsToVisit.add(Map.entry(i, j));

        intersectionsToVisit.parallelStream()
                .forEach(point -> updateIntersections(intersections, lines.get(point.getKey()), lines.get(point.getValue())));

        synchronized (this) {
            return intersections;
        }
    }

    private void updateIntersections(HashMap<Point, Integer> intersections, Line line1, Line line2) {
        var commonPoints = new HashSet<>(line1.getCoveredPoints());
        commonPoints.retainAll(line2.getCoveredPoints());
        synchronized (this) {
            for (var commonPoint : commonPoints) {
                var currentCount = intersections.getOrDefault(commonPoint, 0);
                intersections.put(commonPoint, currentCount + 1);
            }
        }
    }
}
