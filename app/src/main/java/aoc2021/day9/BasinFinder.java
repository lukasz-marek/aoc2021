package aoc2021.day9;

import java.util.*;
import java.util.stream.Collectors;

public class BasinFinder {
    public List<Basin> find(int[][] heightmap, List<Coordinates> lowPoints) {
        return lowPoints.stream().map(point -> findBasin(heightmap, point)).collect(Collectors.toList());
    }

    private Basin findBasin(int[][] heightmap, Coordinates lowPoint) {
        var pointsToVisit = new ArrayDeque<Coordinates>();
        pointsToVisit.add(lowPoint);
        var basinMembers = new HashSet<Coordinates>();

        while (!pointsToVisit.isEmpty()) {
            var maybeBasinMember = pointsToVisit.pop();
            if (!basinMembers.contains(maybeBasinMember)) {
                var nextStates = generateNextPointsToVisit(heightmap, maybeBasinMember);
                if (!nextStates.isEmpty()) {
                    basinMembers.add(maybeBasinMember);
                    pointsToVisit.addAll(nextStates);
                }
            }
        }

        return new Basin(basinMembers);
    }

    private List<Coordinates> generateNextPointsToVisit(int[][] heightmap, Coordinates maybeBasinMember) {
        if (maybeBasinMember.getHeight() == 9)
            return Collections.emptyList();

        var newStates = new ArrayList<Coordinates>();
        for (var x = maybeBasinMember.getX() - 1; x <= maybeBasinMember.getX() + 1; x++) {
            if (x < heightmap.length && x >= 0) {
                for (var y = maybeBasinMember.getY() - 1; y <= maybeBasinMember.getY() + 1; y++) {
                    if (y < heightmap[x].length && y >= 0) {
                        if (x == maybeBasinMember.getX() || y == maybeBasinMember.getY()) {
                            if (heightmap[x][y] >= maybeBasinMember.getHeight())
                                newStates.add(new Coordinates(x, y, heightmap[x][y]));
                        }
                    }
                }
            }
        }
        return newStates;
    }
}
