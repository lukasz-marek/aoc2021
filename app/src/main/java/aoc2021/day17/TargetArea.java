package aoc2021.day17;

public final class TargetArea {
    private final int fromX;
    private final int toX;
    private final int fromY;
    private final int toY;

    public TargetArea(int fromX, int toX, int fromY, int toY) {
        this.fromX = fromX;
        this.toX = toX;
        this.fromY = fromY;
        this.toY = toY;
    }

    public int getFromX() {
        return fromX;
    }

    public int getToX() {
        return toX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToY() {
        return toY;
    }

    public boolean contains(Position position) {
        return fromX <= position.getX() && toX >= position.getX() && fromY <= position.getY() && toY >= position.getY();
    }

    public boolean isReachable(Position position, Velocity velocity) {
        var onLeft = position.getX() < fromX;
        var onRight = position.getX() > toX;
        var movingLeft = velocity.getX() < 0;
        var movingRight = velocity.getX() > 0;
        var reachedHorizontally = fromX <= position.getX() && toX >= position.getX();

        var canReachHorizontally = (onLeft && movingRight) || (onRight && movingLeft) || reachedHorizontally;

        var below = position.getY() < fromY;
        var movingUp = velocity.getY() > 0;
        var above = position.getY() > toY;
        var reachedVertically = fromY <= position.getY() && toY >= position.getY();

        var canReachVertically = (below && movingUp) || above || reachedVertically;

        return canReachHorizontally && canReachVertically;
    }
}
