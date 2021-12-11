package aoc2021.day8;

import java.util.Set;

public class DigitDecoder {

    public int decodeOutput(SegmentIO segmentIO) {
        return -1;
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
