package aoc2021.day8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DigitDecoder {
    private final DigitIdentifier digitIdentifier;

    public DigitDecoder(DigitIdentifier digitIdentifier) {
        this.digitIdentifier = digitIdentifier;
    }

    public int decode(SegmentIO segmentIO) {
        var segmentToSignal = createInitialGuesses();
        limitByInputs(segmentIO.getInput(), segmentToSignal);
        limitByOutputs(segmentIO.getOutput(), segmentToSignal);
        var decodedSegmentToSignal = findValidMapping(segmentToSignal, segmentIO);
        return decodeOutput(decodedSegmentToSignal, segmentIO.getOutput());
    }

    private int decodeOutput(Map<Character, Character> segmentToSignal, List<DisplayOutput> outputs) {
        var result = 0;
        var multiplier = (int) Math.pow(10, outputs.size() - 1);
        for (var output : outputs) {
            for (var digit = 0; digit <= 9; digit++) {
                var digitSegments = segmentsForDigit(digit);
                var digitSignals = digitSegments.stream().map(segmentToSignal::get).collect(Collectors.toUnmodifiableSet());
                if (digitSignals.equals(output.getSignals())) {
                    result += digit * multiplier;
                    break;
                }
            }
            multiplier /= 10;
        }
        return result;
    }

    public Map<Character, Character> findValidMapping(Map<Character, Set<Character>> guesses, SegmentIO segmentIO) {
        return findValidMapping(guesses, new HashMap<>(), segmentIO);
    }

    public Map<Character, Character> findValidMapping(Map<Character, Set<Character>> guesses, Map<Character, Character> assignments, SegmentIO segmentIO) {
        if (assignments.size() == guesses.size()) {
            if (isSolutionValid(assignments, segmentIO))
                return assignments;
            else
                return null;
        } else {
            return searchForSolution(guesses, assignments, segmentIO);
        }
    }

    private boolean isSolutionValid(Map<Character, Character> segmentToSignal, SegmentIO segmentIO) {
        var segmentsToSignals = IntStream.range(0, 10)
                .mapToObj(this::segmentsForDigit)
                .map(segments -> segments.stream().map(segmentToSignal::get).collect(Collectors.toUnmodifiableSet()))
                .collect(Collectors.toUnmodifiableSet());
        for (var input : segmentIO.getInput()) {
            if (!segmentsToSignals.contains(input.getSignals()))
                return false;
        }
        for (var output : segmentIO.getOutput()) {
            if (!segmentsToSignals.contains(output.getSignals()))
                return false;
        }
        return true;
    }

    private Map<Character, Character> searchForSolution(Map<Character, Set<Character>> guesses, Map<Character, Character> assignments, SegmentIO segmentIO) {
        for (var segment : guesses.keySet()) {
            if (!assignments.containsKey(segment)) {
                Map<Character, Character> result = checkAssignment(guesses, assignments, segment, segmentIO);
                if (result != null) return result;
            }
        }
        return null;
    }

    private Map<Character, Character> checkAssignment(Map<Character, Set<Character>> guesses, Map<Character, Character> assignments, Character segment, SegmentIO segmentIO) {
        for (var signal : guesses.get(segment)) {
            assignments.put(segment, signal);
            var result = findValidMapping(guesses, assignments, segmentIO);
            if (result != null)
                return result;
        }
        assignments.remove(segment);
        return null;
    }

    private void limitByInputs(List<DisplayInput> inputs, Map<Character, Set<Character>> guesses) {
        for (var input : inputs) {
            var decoded = digitIdentifier.identify(input);
            decoded.ifPresent(decodedDigit -> limitGuesses(decodedDigit, input.getSignals(), guesses));
        }
    }

    private void limitByOutputs(List<DisplayOutput> inputs, Map<Character, Set<Character>> guesses) {
        for (var input : inputs) {
            var decoded = digitIdentifier.identify(input);
            decoded.ifPresent(decodedDigit -> limitGuesses(decodedDigit, input.getSignals(), guesses));
        }
    }

    private void limitGuesses(int digit, Set<Character> signals, Map<Character, Set<Character>> guesses) {
        for (var segment : segmentsForDigit(digit))
            guesses.get(segment).retainAll(signals);
    }

    // segment to signal mapping
    private Map<Character, Set<Character>> createInitialGuesses() {
        var mappings = new HashMap<Character, Set<Character>>();
        for (var c = 'a'; c <= 'g'; c++) {
            mappings.put(c, new HashSet<>(getInitialPossibleMappings()));
        }
        return mappings;
    }

    private Set<Character> getInitialPossibleMappings() {
        return Set.of('a', 'b', 'c', 'd', 'e', 'f', 'g');
    }

    private Set<Character> segmentsForDigit(int digit) {
        switch (digit) {
            case 0:
                return Set.of('a', 'b', 'c', 'e', 'f', 'g');
            case 1:
                return Set.of('c', 'f');
            case 2:
                return Set.of('a', 'c', 'd', 'e', 'g');
            case 3:
                return Set.of('a', 'c', 'd', 'f', 'g');
            case 4:
                return Set.of('b', 'c', 'd', 'f');
            case 5:
                return Set.of('a', 'b', 'd', 'f', 'g');
            case 6:
                return Set.of('a', 'b', 'd', 'e', 'f', 'g');
            case 7:
                return Set.of('a', 'c', 'f');
            case 8:
                return Set.of('a', 'b', 'c', 'd', 'e', 'f', 'g');
            case 9:
                return Set.of('a', 'b', 'c', 'd', 'f', 'g');
            default:
                throw new IllegalArgumentException(digit + " is not a valid digit");
        }
    }
}
