package aoc2021.day5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionFinder {
    public Map<Point, Integer> findIntersections(List<Line> lines) {
        var intersections = new HashMap<Point, Integer>();

        for (var i = 0; i < lines.size(); i++)
            for (var j = 0; j < i; j++)
                updateIntersections(intersections, lines.get(i), lines.get(j));

        return intersections;
    }

    private void updateIntersections(HashMap<Point, Integer> intersections, Line line1, Line line2) {
        for (var point1 : line1.getCoveredPoints())
            for (var point2 : line2.getCoveredPoints())
                if (point1.equals(point2)) {
                    var currentCount = intersections.getOrDefault(point1, 0);
                    intersections.put(point1, currentCount + 1);
                }
    }
}
