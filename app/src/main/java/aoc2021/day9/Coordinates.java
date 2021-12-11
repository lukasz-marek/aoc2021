package aoc2021.day9;

public final class Coordinates {
    private final int x;
    private final int y;
    private final int height;

    public Coordinates(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }
}
