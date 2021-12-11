package aoc2021.day9;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, height);
    }
}
