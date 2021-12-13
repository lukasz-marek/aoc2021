package aoc2021.day11;

import java.util.*;
import java.util.stream.Collectors;

public class StepExecutor {
    public long execute(int[][] grid, int steps) {
        var totalFlashes = 0L;

        for (var step = 0; step < steps; step++)
            totalFlashes += executeStep(grid);

        return totalFlashes;
    }

    public int executeUntilSynchronization(int[][] grid) {
        var totalItems = Arrays.stream(grid).mapToInt(row -> row.length).sum();
        var steps = 0;
        var flashes = -1;
        do {
            steps++;
            flashes = executeStep(grid);
        }while (flashes != totalItems);
        return steps;
    }

    private int executeStep(int[][] grid) {
        increaseAllByOne(grid);
        var flashed = flash(grid);
        dischargeFlashed(grid, flashed);
        return flashed.size();
    }

    private void dischargeFlashed(int[][] grid, Set<Coordinates> flashed) {
        flashed.forEach(coordinates -> grid[coordinates.getX()][coordinates.getY()] = 0);
    }

    private Set<Coordinates> flash(int[][] grid) {
        var flashed = new HashSet<Coordinates>();

        for (var i = 0; i < grid.length; i++)
            for (var j = 0; j < grid[i].length; j++) {
                var currentCoordinates = new Coordinates(i, j);
                flashSingle(grid, flashed, currentCoordinates);
            }

        return flashed;
    }

    private void flashSingle(int[][] grid, HashSet<Coordinates> flashed, Coordinates currentCoordinates) {

        if (!flashed.contains(currentCoordinates) && grid[currentCoordinates.getX()][currentCoordinates.getY()] > 9) {
            var toBeFlashed = new ArrayDeque<Coordinates>();
            toBeFlashed.add(currentCoordinates);

            while (!toBeFlashed.isEmpty()) {
                var flashing = toBeFlashed.pop();
                var neighboursToFlash = keepFlashing(grid, flashed, flashing);

                toBeFlashed.addAll(neighboursToFlash);
                flashed.add(flashing);
                toBeFlashed.removeIf(flashed::contains);
            }
        }
    }

    private List<Coordinates> keepFlashing(int[][] grid, HashSet<Coordinates> flashed, Coordinates flashing) {
        var neighbours = getNeighbours(grid, flashing);
        neighbours.forEach(coordinates -> grid[coordinates.getX()][coordinates.getY()] += 1);

        return neighbours.stream()
                .filter(coordinates -> grid[coordinates.getX()][coordinates.getY()] > 9)
                .filter(coordinates -> !flashed.contains(coordinates))
                .collect(Collectors.toUnmodifiableList());
    }

    private void increaseAllByOne(int[][] grid) {
        for (var i = 0; i < grid.length; i++)
            for (var j = 0; j < grid[i].length; j++)
                grid[i][j] += 1;
    }

    private List<Coordinates> getNeighbours(int[][] grid, Coordinates coordinates) {
        var neighbours = new ArrayList<Coordinates>();
        var x = coordinates.getX();
        var y = coordinates.getY();

        for (var neighbourX = x - 1; neighbourX <= x + 1; neighbourX++)
            if (neighbourX >= 0 && neighbourX < grid.length)
                for (var neighbourY = y - 1; neighbourY <= y + 1; neighbourY++)
                    if (neighbourY >= 0 && neighbourY < grid[x].length)
                        if (neighbourY != y || neighbourX != x)
                            neighbours.add(new Coordinates(neighbourX, neighbourY));

        return neighbours;
    }
}
