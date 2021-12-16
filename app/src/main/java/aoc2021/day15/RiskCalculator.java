package aoc2021.day15;

public class RiskCalculator {
    public long calculateRisk(Path path, int[][] map) {
        return path.getPoints().stream().mapToLong(point -> map[point.getX()][point.getY()]).sum();
    }
}
