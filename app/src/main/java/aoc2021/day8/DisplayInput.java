package aoc2021.day8;

import java.util.Set;

public final class DisplayInput {
    private final Set<Character> signals;

    public DisplayInput(Set<Character> signals) {
        this.signals = Set.copyOf(signals);
    }

    public Set<Character> getSignals() {
        return signals;
    }
}
