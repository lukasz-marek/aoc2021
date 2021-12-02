package aoc2021.day2;

public class Up implements Step {
    private final int offset;

    public Up(int offset) {
        this.offset = offset;
    }

    @Override
    public Position take(Position currentPosition) {
        return new Position(currentPosition.getHorizontal(), currentPosition.getDepth() - offset);
    }
}
