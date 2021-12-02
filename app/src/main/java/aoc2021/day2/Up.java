package aoc2021.day2;

import java.util.Objects;

public class Up implements Step {
    private final int offset;

    public Up(int offset) {
        this.offset = offset;
    }

    @Override
    public Position take(Position currentPosition) {
        return new Position(currentPosition.getHorizontal(), currentPosition.getDepth() - offset);
    }

    @Override
    public AimedPosition take(AimedPosition currentPosition) {
        return new AimedPosition(currentPosition.getHorizontal(), currentPosition.getDepth(), currentPosition.getAim() - offset);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Up up = (Up) o;
        return offset == up.offset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(offset);
    }

    @Override
    public String toString() {
        return "Up{" +
                "offset=" + offset +
                '}';
    }
}
