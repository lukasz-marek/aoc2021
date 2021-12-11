package aoc2021.day8;

import java.util.Set;

public class DisplayOutput {
    private final Set<Character> signals;

    public DisplayOutput(Set<Character> signals) {
        this.signals = Set.copyOf(signals);
    }

    public Set<Character> getSignals() {
        return signals;
    }
}
