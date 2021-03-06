package aoc2021.day5;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public final class Line {
    private final Point from;
    private final Point to;
    private final Set<Point> coveredPoints;

    public Line(Point from, Point to) {
        this.from = from;
        this.to = to;
        this.coveredPoints = computeCoveredPoints();
    }

    public boolean isHorizontal() {
        return from.getY() == to.getY();
    }

    public boolean isVertical() {
        return from.getX() == to.getX();
    }

    public Set<Point> getCoveredPoints() {
        return coveredPoints;
    }

    public Set<Point> computeCoveredPoints() {
        var xCoordinateStart = Math.min(from.getX(), to.getX());
        var xCoordinateEnd = Math.max(from.getX(), to.getX());
        var yCoordinateStart = Math.min(from.getY(), to.getY());
        var yCoordinateEnd = Math.max(from.getY(), to.getY());

        var points = new LinkedHashSet<Point>();

        for (var x = xCoordinateStart; x <= xCoordinateEnd; x++)
            for (var y = yCoordinateStart; y <= yCoordinateEnd; y++)
                points.add(new Point(x, y));

        return Collections.unmodifiableSet(points);
    }
}
