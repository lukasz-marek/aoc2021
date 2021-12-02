package aoc2021.day2;

public class Position {
    private final int horizontal;
    private final int depth;

    public Position() {
        this(0, 0);
    }

    public Position(int horizontal, int depth) {
        this.horizontal = horizontal;
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public int getHorizontal() {
        return horizontal;
    }
}
