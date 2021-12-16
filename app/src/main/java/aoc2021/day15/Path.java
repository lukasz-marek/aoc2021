package aoc2021.day15;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Path implements Comparable<Path> {
    private final List<Point> points;
    private final Set<Point> uniquePoints;
    private final long risk;

    public Path(ArrayList<Point> points, long risk) {
        this.points = points;
        this.uniquePoints = new HashSet<>(points);
        this.risk = risk;
    }

    public List<Point> getPoints() {
        return points;
    }

    public Path withPoint(Point point, long pointRisk) {
        var newPoints = new ArrayList<>(points);
        newPoints.add(point);
        return new Path(newPoints, this.risk + pointRisk);
    }

    public Set<Point> getUniquePoints() {
        return uniquePoints;
    }

    @Override
    public int compareTo(Path o) {
        return Long.compare(this.risk, o.risk);
    }
}
