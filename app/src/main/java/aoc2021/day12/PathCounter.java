package aoc2021.day12;

import java.util.*;
import java.util.function.BiFunction;

public class PathCounter {
    private final static String PATH_START = "start";
    private final static String PATH_END = "end";

    public long countPaths(Map<String, Set<String>> waypoints, BiFunction<String, Map<String, Integer>, Boolean> visitationPredicate) {
        return forkFromEndpoint(PATH_START, waypoints, Collections.emptyMap(), visitationPredicate);
    }

    private long forkFromEndpoint(String waypoint, Map<String, Set<String>> waypoints, Map<String, Integer> smallCaveVisits, BiFunction<String, Map<String, Integer>, Boolean> canVisit) {
        if (waypoint.equals(PATH_END)) return 1L;

        return countForkingPaths(waypoint, waypoints, smallCaveVisits, canVisit);
    }

    private int countForkingPaths(String waypoint, Map<String, Set<String>> waypoints, Map<String, Integer> smallCaveVisits, BiFunction<String, Map<String, Integer>, Boolean> canVisit) {
        var currentPath = getNextPath(waypoint, smallCaveVisits);
        var totalPaths = 0;
        for (var nextWaypoint : waypoints.getOrDefault(waypoint, Collections.emptySet()))
            if (canVisit.apply(nextWaypoint, currentPath))
                totalPaths += forkFromEndpoint(nextWaypoint, waypoints, currentPath, canVisit);
        return totalPaths;
    }

    private Map<String, Integer> getNextPath(String waypoint, Map<String, Integer> path) {
        if (!isBig(waypoint)) {
            var currentPath = new HashMap<>(path);
            updateSmallCaveVisits(currentPath, waypoint);
            return Collections.unmodifiableMap(currentPath);
        } else {
            return path;
        }
    }

    private void updateSmallCaveVisits(Map<String, Integer> smallCaveVisits, String waypoint) {
        var previousValue = smallCaveVisits.getOrDefault(waypoint, 0);
        smallCaveVisits.put(waypoint, previousValue + 1);
    }

    private boolean isBig(String waypoint) {
        return !waypoint.toLowerCase().equals(waypoint);
    }
}
