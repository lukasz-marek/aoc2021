package aoc2021.day12;

import java.util.*;
import java.util.function.BiFunction;

public class PathCounter {
    private final static String PATH_START = "start";
    private final static String PATH_END = "end";

    public long countPaths(Map<String, Set<String>> waypoints, int maxVisitations) {
        var predicate = provideVisitationPredicate(maxVisitations);
        return forkFromEndpoint(PATH_START, waypoints, Collections.emptyMap(), predicate);
    }

    private BiFunction<String, Map<String, Integer>, Boolean> provideVisitationPredicate(int maxVisitations) {
        return (waypoint, visitationCounter) -> isBig(waypoint) || visitationCounter.getOrDefault(waypoint, 0) <= maxVisitations;
    }

    private long forkFromEndpoint(String waypoint, Map<String, Set<String>> waypoints, Map<String, Integer> path, BiFunction<String, Map<String, Integer>, Boolean> canVisit) {
        if (waypoint.equals(PATH_END)) return 1L;

        return countForkingPaths(waypoint, waypoints, path, canVisit);
    }

    private int countForkingPaths(String waypoint, Map<String, Set<String>> waypoints, Map<String, Integer> path, BiFunction<String, Map<String, Integer>, Boolean> canVisit) {
        var currentPath = new HashMap<>(path);
        updatePath(currentPath, waypoint);
        var totalPaths = 0;
        for (var nextWaypoint : waypoints.getOrDefault(waypoint, Collections.emptySet()))
            if (canVisit.apply(waypoint, currentPath))
                totalPaths += forkFromEndpoint(nextWaypoint, waypoints, currentPath, canVisit);
        return totalPaths;
    }

    private void updatePath(Map<String, Integer> path, String waypoint) {
        var previousValue = path.getOrDefault(waypoint, 0);
        path.put(waypoint, previousValue + 1);
    }

    private boolean isBig(String waypoint) {
        return !waypoint.toLowerCase().equals(waypoint);
    }
}
