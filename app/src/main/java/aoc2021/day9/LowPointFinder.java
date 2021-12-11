package aoc2021.day9;

import java.util.ArrayList;
import java.util.List;

public class LowPointFinder {
    public List<Coordinates> find(int[][] heightmap) {
        var lowPoints = new ArrayList<Coordinates>();
        for (var x = 0; x < heightmap.length; x++) {
            for (var y = 0; y < heightmap[x].length; y++) {
                var isLowPoint = checkLowPoint(heightmap, x, y);
                if (isLowPoint)
                    lowPoints.add(new Coordinates(x, y, heightmap[x][y]));
            }
        }
        return lowPoints;
    }

    private boolean checkLowPoint(int[][] heightmap, int x, int y) {
        for (var neighbourX = x - 1; neighbourX <= x + 1; neighbourX++) {
            for (var neighbourY = y - 1; neighbourY <= y + 1; neighbourY++) {
                if (neighbourX >= 0 && neighbourX < heightmap.length) {
                    if (neighbourY >= 0 && neighbourY < heightmap[x].length) {
                        var same = neighbourX == x && neighbourY == y;
                        var diagonal = neighbourX != x && neighbourY != y;
                        if (!same && !diagonal && heightmap[x][y] >= heightmap[neighbourX][neighbourY])
                            return false;
                    }
                }
            }
        }
        return true;
    }
}
