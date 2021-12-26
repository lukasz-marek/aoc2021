package aoc2021.day17;

import java.util.OptionalInt;

public class Simulator {

    public OptionalInt simulate(Velocity velocity, TargetArea targetArea) {
        var currentPosition = new Position(0, 0);
        var maxHeight = 0;
        var contained = false;
        while (targetArea.isReachable(currentPosition, velocity) || targetArea.contains(currentPosition)) {
            contained = contained || targetArea.contains(currentPosition);
            currentPosition = nextPosition(velocity, currentPosition);
            maxHeight = Math.max(maxHeight, currentPosition.getY());
            velocity = nextVelocity(velocity);
        }

        if (contained)
            return OptionalInt.of(maxHeight);
        else
            return OptionalInt.empty();
    }

    private Position nextPosition(Velocity velocity, Position currentPosition) {
        return new Position(currentPosition.getX() + velocity.getX(), currentPosition.getY() + velocity.getY());
    }

    private Velocity nextVelocity(Velocity currentVelocity) {
        var nextX = 0;
        if (currentVelocity.getX() > 0)
            nextX = currentVelocity.getX() - 1;
        else if (currentVelocity.getX() < 0)
            nextX = currentVelocity.getX() + 1;

        var nextY = currentVelocity.getY() - 1;
        return new Velocity(nextX, nextY);
    }
}
