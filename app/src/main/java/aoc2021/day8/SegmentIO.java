package aoc2021.day8;

import java.util.List;

public class SegmentIO {
    private final List<DisplayInput> input;
    private final List<DisplayOutput> output;

    public SegmentIO(List<DisplayInput> input, List<DisplayOutput> output) {
        this.input = input;
        this.output = output;
    }

    public List<DisplayInput> getInput() {
        return input;
    }

    public List<DisplayOutput> getOutput() {
        return output;
    }
}
