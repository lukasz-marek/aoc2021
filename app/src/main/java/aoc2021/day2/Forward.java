package aoc2021.day2;

public class Forward implements Step {
    private final int offset;

    public Forward(int offset) {
        this.offset = offset;
    }

    @Override
    public Position take(Position currentPosition) {
        return new Position(currentPosition.getHorizontal() + offset, currentPosition.getDepth());
    }
}
