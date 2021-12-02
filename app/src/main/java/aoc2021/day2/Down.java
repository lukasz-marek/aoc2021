package aoc2021.day2;

import java.util.Objects;

public class Down implements Step {
    private final int offset;

    public Down(int offset) {
        this.offset = offset;
    }

    @Override
    public Position take(Position currentPosition) {
        return new Position(currentPosition.getHorizontal(), currentPosition.getDepth() + offset);
    }

    @Override
    public String toString() {
        return "Down{" +
                "offset=" + offset +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Down down = (Down) o;
        return offset == down.offset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(offset);
    }
}
