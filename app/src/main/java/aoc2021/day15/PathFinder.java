package aoc2021.day15;

import java.util.*;
import java.util.stream.Collectors;

public class PathFinder {
    public Path findPathWithLowestRisk(int[][] map) {
        var start = new Point(0, 0);
        var end = new Point(map.length - 1, map[map.length - 1].length - 1);
        return findPath(start, end, map);
    }

    private Path findPath(Point start, Point end, int[][] map) {
        var distances = initializeDistances(start, map);
        var unvisited = initializeUnvisitedPoints(map);
        var paths = initializePaths(map);

        while (!unvisited.isEmpty())
            visitPoint(map, distances, unvisited, paths);

        return paths.get(end);
    }

    private void visitPoint(int[][] map, Map<Point, Integer> distances, Set<Point> unvisited, Map<Point, Path> paths) {
        var current = getCurrentPoint(unvisited, distances);
        unvisited.remove(current);

        var unvisitedNeighbours = getUnvisitedNeighbours(map, unvisited, current);
        unvisitedNeighbours.forEach(neighbour -> updateDistance(distances, current, neighbour, map, paths));
    }

    private List<Point> getUnvisitedNeighbours(int[][] map, Set<Point> unvisited, Point current) {
        return getNearby(current, map).stream().filter(unvisited::contains).collect(Collectors.toUnmodifiableList());
    }

    private Point getCurrentPoint(Set<Point> unvisited, Map<Point, Integer> distances) {
        return unvisited.stream().min(Comparator.comparing(distances::get)).get();
    }

    private void updateDistance(Map<Point, Integer> distances, Point point, Point neighbour, int[][] map, Map<Point, Path> paths) {
        var currentDistance = distances.get(neighbour);
        var newDistance = distances.get(point) + map[neighbour.getX()][neighbour.getY()];
        if (currentDistance > newDistance) {
            distances.put(neighbour, newDistance);
            paths.put(neighbour, paths.get(point).withPoint(neighbour));
        }
    }

    private List<Point> getNearby(Point point, int[][] map) {
        var neighbours = new ArrayList<Point>(4);
        for (var x = point.getX() - 1; x <= point.getX() + 1; x++)
            for (var y = point.getY() - 1; y <= point.getY() + 1; y++)
                if (x >= 0 && y >= 0)
                    if (x < map.length && y < map[x].length)
                        if (x == point.getX() || y == point.getY())
                            neighbours.add(new Point(x, y));
        return neighbours;
    }

    private Map<Point, Path> initializePaths(int[][] map) {
        var paths = new LinkedHashMap<Point, Path>();
        for (var x = 0; x < map.length; x++)
            for (var y = 0; y < map[x].length; y++)
                paths.put(new Point(x, y), new Path(Collections.emptyList()));
        return paths;
    }

    private Map<Point, Integer> initializeDistances(Point start, int[][] map) {
        var distances = new LinkedHashMap<Point, Integer>();
        for (var x = 0; x < map.length; x++)
            for (var y = 0; y < map[x].length; y++)
                distances.put(new Point(x, y), Integer.MAX_VALUE);
        distances.put(start, 0);
        return distances;
    }

    private Set<Point> initializeUnvisitedPoints(int[][] map) {
        var unvisited = new LinkedHashSet<Point>();
        for (var x = 0; x < map.length; x++)
            for (var y = 0; y < map[x].length; y++)
                unvisited.add(new Point(x, y));
        return unvisited;
    }
}