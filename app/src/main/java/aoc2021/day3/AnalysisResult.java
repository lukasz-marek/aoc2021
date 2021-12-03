package aoc2021.day3;

public final class AnalysisResult {
    private final long gamma;
    private final long epsilon;

    public AnalysisResult(long gamma, long epsilon) {
        this.gamma = gamma;
        this.epsilon = epsilon;
    }

    public long getEpsilon() {
        return epsilon;
    }

    public long getGamma() {
        return gamma;
    }
}
