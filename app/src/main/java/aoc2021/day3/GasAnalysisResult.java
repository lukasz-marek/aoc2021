package aoc2021.day3;

import java.util.Objects;

public class GasAnalysisResult {
    private final long generatorRating;
    private final long scrubberRating;

    public GasAnalysisResult(long generatorRating, long scrubberRating) {
        this.generatorRating = generatorRating;
        this.scrubberRating = scrubberRating;
    }

    public long getGeneratorRating() {
        return generatorRating;
    }

    public long getScrubberRating() {
        return scrubberRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GasAnalysisResult that = (GasAnalysisResult) o;
        return generatorRating == that.generatorRating && scrubberRating == that.scrubberRating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(generatorRating, scrubberRating);
    }

    @Override
    public String toString() {
        return "GasAnalysisResult{" +
                "generatorRating=" + generatorRating +
                ", scrubberRating=" + scrubberRating +
                '}';
    }
}
