package aoc2021.day12;

import java.util.*;

public class PathCounter {
    private final static String PATH_START = "start";
    private final static String PATH_END = "end";

    public long countPaths(Map<String, Set<String>> waypoints) {
        return forkFromEndpoint(PATH_START, waypoints, Collections.emptyList());
    }

    private long forkFromEndpoint(String waypoint, Map<String, Set<String>> waypoints, List<String> path) {
        if (waypoint.equals(PATH_END))
            return 1L;

        return countForkingPaths(waypoint, waypoints, path);
    }

    private int countForkingPaths(String waypoint, Map<String, Set<String>> waypoints, List<String> path) {
        var currentPath = new ArrayList<>(path);
        currentPath.add(waypoint);
        var totalPaths = 0;
        for (var nextWaypoint : waypoints.getOrDefault(waypoint, Collections.emptySet()))
            if (!path.contains(nextWaypoint) || !isSmall(nextWaypoint))
                totalPaths += forkFromEndpoint(nextWaypoint, waypoints, currentPath);
        return totalPaths;
    }

    private boolean isSmall(String waypoint) {
        return waypoint.toLowerCase().equals(waypoint);
    }
}
