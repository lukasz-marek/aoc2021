package aoc2021.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class PathFinder {
    public Path findPathWithLowestRisk(int[][] map) {
        var start = new Point(0, 0);
        var end = new Point(map.length, map[map.length - 1].length);
        return findPath(start, end, map);
    }

    private Path findPath(Point start, Point end, int[][] map) {
        var queue = getInitialQueue(start, map);

        while (!queue.isEmpty()) {
            var currentPath = queue.poll();
            if (isPointReached(currentPath, end)) return currentPath;

            var lastPoint = getLastVisitedPoint(currentPath);
            var pointsToVisit = getPointsToVisit(map, currentPath, lastPoint);

            pointsToVisit.forEach(point -> queue.add(currentPath.withPoint(point, map[point.getX()][point.getY()])));
        }

        throw new IllegalStateException("Failed to find a path");
    }

    private boolean isPointReached(Path currentPath, Point end) {
        return currentPath.getUniquePoints().contains(end);
    }

    private Point getLastVisitedPoint(Path currentPath) {
        return currentPath.getPoints().get(currentPath.getPoints().size() - 1);
    }

    private Stream<Point> getPointsToVisit(int[][] map, Path currentPath, Point lastPoint) {
        return getAdjacentPoints(lastPoint, map).stream()
                .filter(point -> !isPointReached(currentPath, point));
    }

    private PriorityQueue<Path> getInitialQueue(Point start, int[][] map) {
        var queue = new PriorityQueue<Path>();
        var initialPath = new ArrayList<Point>();
        initialPath.add(start);
        queue.add(new Path(initialPath, map[0][0]));
        return queue;
    }

    private List<Point> getAdjacentPoints(Point point, int[][] map) {
        var points = new ArrayList<Point>(4);
        for (var x = point.getX() - 1; x <= point.getX() + 1; x++)
            if (x >= 0 && x < map.length)
                for (var y = point.getY() - 1; y <= point.getY() + 1; y++)
                    if (y >= 0 && y < map[x].length)
                        if (x == point.getX() || y == point.getY())
                            if (x != point.getX() || y != point.getY())
                                points.add(new Point(x, y));
        return points;
    }
}