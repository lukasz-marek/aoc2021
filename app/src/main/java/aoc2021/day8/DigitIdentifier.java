package aoc2021.day8;

import java.util.OptionalInt;

public class DigitIdentifier {
    public OptionalInt identify(DisplayOutput digit) {
        return identify(digit.getSignals().size());
    }


    public OptionalInt identify(DisplayInput displayInput) {
        return identify(displayInput.getSignals().size());
    }

    private OptionalInt identify(int numberOfSegments) {
        switch (numberOfSegments) {
            case 2:
                return OptionalInt.of(1);
            case 4:
                return OptionalInt.of(4);
            case 3:
                return OptionalInt.of(7);
            case 7:
                return OptionalInt.of(8);
            default:
                return OptionalInt.empty();
        }
    }
}
