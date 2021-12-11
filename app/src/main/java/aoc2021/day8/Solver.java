package aoc2021.day8;

import aoc2021.common.InputLoader;

import java.util.List;
import java.util.OptionalInt;

public class Solver {
    private final InputLoader inputLoader;
    private final InputConverter inputConverter;
    private final DigitIdentifier digitIdentifier;
    private final DigitDecoder digitDecoder;

    public Solver() {
        inputLoader = new InputLoader();
        inputConverter = new InputConverter();
        digitIdentifier = new DigitIdentifier();
        digitDecoder = new DigitDecoder(digitIdentifier);
    }

    public int solvePart1() {
        var rawData = inputLoader.loadInput("day8_1.txt");
        var input = inputConverter.convert(rawData);
        var identifiedDigitsCount = input.stream()
                .map(SegmentIO::getOutput)
                .flatMap(List::stream)
                .map(digitIdentifier::identify)
                .filter(OptionalInt::isPresent)
                .count();
        return (int) identifiedDigitsCount;
    }

    public int solvePart2() {
        var rawData = inputLoader.loadInput("day8_1.txt");
        var input = inputConverter.convert(rawData);
        return input.parallelStream().mapToInt(digitDecoder::decode).sum();
    }
}
