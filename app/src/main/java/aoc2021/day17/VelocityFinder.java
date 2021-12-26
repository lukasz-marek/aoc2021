package aoc2021.day17;

import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class VelocityFinder {

    private final Simulator simulator;

    public VelocityFinder(Simulator simulator) {
        this.simulator = simulator;
    }

    public Optional<Integer> findHighestPosition(TargetArea targetArea) {
        var maxX = Math.max(Math.abs(targetArea.getFromX()), Math.abs(targetArea.getToX()));
        var maxY = Math.max(Math.abs(targetArea.getFromY()), Math.abs(targetArea.getToY()));
        var initialVelocities = Stream.<Velocity>builder();
        for (var x = -maxX; x <= maxX; x++)
            for (var y = -maxY; y <= maxY; y++)
                initialVelocities.add(new Velocity(x, y));

        return initialVelocities.build()
                .sequential()
                .map(velocity -> simulator.simulate(velocity, targetArea))
                .filter(OptionalInt::isPresent)
                .map(OptionalInt::getAsInt)
                .max(Comparator.naturalOrder());
    }

    public long countValidVelocities(TargetArea targetArea) {
        var maxX = Math.max(Math.abs(targetArea.getFromX()), Math.abs(targetArea.getToX()));
        var maxY = Math.max(Math.abs(targetArea.getFromY()), Math.abs(targetArea.getToY()));
        var initialVelocities = Stream.<Velocity>builder();
        for (var x = -maxX; x <= maxX; x++)
            for (var y = -maxY; y <= maxY; y++)
                initialVelocities.add(new Velocity(x, y));

        return initialVelocities.build()
                .sequential()
                .map(velocity -> simulator.simulate(velocity, targetArea))
                .filter(OptionalInt::isPresent)
                .count();
    }
}
