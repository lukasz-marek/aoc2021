package aoc2021.day18;

import java.util.Optional;
import java.util.regex.Pattern;

public class NodeParser {
    private final Pattern simplePairPattern = Pattern.compile("^\\[(\\d+),(\\d+)]$");
    private final Pattern numberOnLeftPattern = Pattern.compile("^\\[(\\d+),(\\[.+])]$");
    private final Pattern numberOnRightPattern = Pattern.compile("^\\[(\\[.+]),(\\d+)]$");
    private final Pattern pairOnBothSidesPattern = Pattern.compile("^\\[(.+)]$");

    public Node parse(String value) {
        return tryParseSimpleNode(value)
                .or(() -> tryParseNumberOnLeft(value))
                .or(() -> tryParseNumberOnRight(value))
                .or((() -> tryParsePairOnBoth(value)))
                .orElseThrow();
    }

    private Optional<Pair> tryParseSimpleNode(String value) {
        var matcher = simplePairPattern.matcher(value);
        if (matcher.find()) {
            var left = Integer.parseInt(matcher.group(1));
            var right = Integer.parseInt(matcher.group(2));
            return Optional.of(Pair.of(left, right));
        }
        return Optional.empty();
    }

    private Optional<Pair> tryParseNumberOnLeft(String value) {
        var matcher = numberOnLeftPattern.matcher(value);
        if (matcher.find()) {
            var left = Integer.parseInt(matcher.group(1));
            var right = parse(matcher.group(2));
            return Optional.of(Pair.of(Leaf.of(left), right));
        }
        return Optional.empty();
    }

    private Optional<Pair> tryParseNumberOnRight(String value) {
        var matcher = numberOnRightPattern.matcher(value);
        if (matcher.find()) {
            var left = parse(matcher.group(1));
            var right = Integer.parseInt(matcher.group(2));
            return Optional.of(Pair.of(left, Leaf.of(right)));
        }
        return Optional.empty();
    }

    private Optional<Pair> tryParsePairOnBoth(String value) {
        var matcher = pairOnBothSidesPattern.matcher(value);
        if (matcher.find()) {
            var match = matcher.group(1);
            var splitAt = identifySplitIndex(match);
            var left = parse(match.substring(0, splitAt));
            var right = parse(match.substring(splitAt + 1));
            return Optional.of(Pair.of(left, right));
        }
        return Optional.empty();
    }

    private int identifySplitIndex(String value) {
        var bracketDepth = 0;
        var index = 0;
        do {
            if (value.charAt(index) == '[')
                bracketDepth++;
            else if (value.charAt(index) == ']')
                bracketDepth--;
            index++;
        } while (bracketDepth != 0);
        return index;
    }
}
