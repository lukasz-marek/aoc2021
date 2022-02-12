package aoc2021.day5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class IntersectionFinder {
    public long findIntersections(List<Line> lines) {
        var intersectionsToVisit = new ArrayList<Map.Entry<Integer, Integer>>();

        for (var i = 0; i < lines.size(); i++)
            for (var j = 0; j < i; j++)
                intersectionsToVisit.add(Map.entry(i, j));

        return intersectionsToVisit.parallelStream()
                .flatMap(point -> getIntersections(lines.get(point.getKey()), lines.get(point.getValue())))
                .distinct()
                .count();
    }

    private Stream<Point> getIntersections(Line line1, Line line2) {
        var commonPoints = new HashSet<>(line1.getCoveredPoints());
        commonPoints.retainAll(line2.getCoveredPoints());

        var result = Stream.<Point>builder();
        for (var commonPoint : commonPoints)
            result.add(commonPoint);
        return result.build();
    }
}
