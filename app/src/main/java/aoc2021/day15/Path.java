package aoc2021.day15;

import java.util.ArrayList;
import java.util.List;

public final class Path {
    private final List<Point> points;

    public Path(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public Path withPoint(Point point) {
        var newPoints = new ArrayList<Point>(this.points);
        newPoints.add(point);
        return new Path(newPoints);
    }
}
