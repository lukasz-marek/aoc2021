package aoc2021.day9;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
            if (!basinMembers.contains(maybeBasinMember) && isBasinMember(heightmap, maybeBasinMember)) {
                basinMembers.add(maybeBasinMember);
                pointsToVisit.addAll(generateStatesToVisit(heightmap, maybeBasinMember));
            }
        }

        return new Basin(basinMembers);
    }

    private boolean isBasinMember(int[][] heightmap, Coordinates maybeBasinMember) {
        if (maybeBasinMember.getHeight() == 9)
            return false;

        for (var x = maybeBasinMember.getX() - 1; x <= maybeBasinMember.getX() + 1; x++) {
            if (x < heightmap.length && x >= 0) {
                for (var y = maybeBasinMember.getY() - 1; y <= maybeBasinMember.getY() + 1; y++) {
                    if (y < heightmap[x].length && y >= 0) {
                        if (x == maybeBasinMember.getX() || y == maybeBasinMember.getY()) {
                            if (heightmap[x][y] >= maybeBasinMember.getHeight())
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private List<Coordinates> generateStatesToVisit(int[][] heightmap, Coordinates maybeBasinMember) {
        var newStates = new ArrayList<Coordinates>();
        for (var x = maybeBasinMember.getX() - 1; x <= maybeBasinMember.getX() + 1; x++) {
            if (x < heightmap.length && x >= 0) {
                for (var y = maybeBasinMember.getY() - 1; y <= maybeBasinMember.getY() + 1; y++) {
                    if (y < heightmap[x].length && y >= 0) {
                        if (x == maybeBasinMember.getX() || y == maybeBasinMember.getY()) {
                            if (heightmap[x][y] >= maybeBasinMember.getHeight()) {
                                newStates.add(new Coordinates(x, y, heightmap[x][y]));
                            }
                        }
                    }
                }
            }
        }
        return newStates;
    }
}
