package aoc2021.day5;

import java.util.ArrayList;
import java.util.Collections;

public final class Line {
    private final Point from;
    private final Point to;

    public Line(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    public boolean isHorizontal() {
        return from.getY() == to.getY();
    }

    public boolean isVertical() {
        return from.getX() == to.getX();
    }

    public Iterable<Point> getCoveredPoints() {
        if (isHorizontal())
            return getHorizontalPoints();
        else if (isVertical())
            return getVerticalPoints();
        else
            return Collections.emptyList();
    }

    private Iterable<Point> getHorizontalPoints() {
        var xCoordinateStart = Math.min(from.getX(), to.getX());
        var xCoordinateEnd = Math.max(from.getX(), to.getX());
        var yCoordinate = from.getY();
        var points = new ArrayList<Point>(Math.abs(from.getX() - to.getX()));

        for (var x = xCoordinateStart; x <= xCoordinateEnd; x++)
            points.add(new Point(x, yCoordinate));

        return points;
    }

    private Iterable<Point> getVerticalPoints() {
        var yCoordinateStart = Math.min(from.getY(), to.getY());
        var yCoordinateEnd = Math.max(from.getY(), to.getY());
        var xCoordinate = from.getX();
        var points = new ArrayList<Point>(Math.abs(from.getY() - to.getY()));

        for (var y = yCoordinateStart; y <= yCoordinateEnd; y++)
            points.add(new Point(xCoordinate, y));

        return points;
    }
}
