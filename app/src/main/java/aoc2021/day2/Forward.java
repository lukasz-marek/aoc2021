package aoc2021.day2;

import java.util.Objects;

public class Forward implements Step {
    private final int offset;

    public Forward(int offset) {
        this.offset = offset;
    }

    @Override
    public Position take(Position currentPosition) {
        return new Position(currentPosition.getHorizontal() + offset, currentPosition.getDepth());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forward forward = (Forward) o;
        return offset == forward.offset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(offset);
    }
}
