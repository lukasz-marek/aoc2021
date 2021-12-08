package aoc2021.day6;

import java.util.Objects;

public final class Lanternfish {
    private final int timer;

    public Lanternfish(int timer) {
        this.timer = timer;
    }

    public Integer getTimer() {
        return timer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lanternfish that = (Lanternfish) o;
        return timer == that.timer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timer);
    }
}
