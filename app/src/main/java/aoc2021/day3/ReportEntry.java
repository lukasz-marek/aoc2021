package aoc2021.day3;

import java.util.Objects;

public final class ReportEntry {
    private final String value;

    public ReportEntry(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntry that = (ReportEntry) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ReportEntry{" +
                "value='" + value + '\'' +
                '}';
    }
}
