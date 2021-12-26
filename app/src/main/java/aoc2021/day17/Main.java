package aoc2021.day17;

public class Main {
    public static void main(String[] args) {
        var targetArea = new TargetArea(281, 311, -74, -54);
        var solver = new VelocityFinder(new Simulator());
        System.out.println("Part 1: " + solver.findHighestPosition(targetArea));
        System.out.println("Part 2: " + solver.countValidVelocities(targetArea));
    }
}
