package aoc2021.day10;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AutoCompleteScorer {
    private final Map<Character, Long> scoreForBrackets = Map.of(')', 1L, ']', 2L, '}', 3L, '>', 4L);

    public long score(List<List<Token>> tokens) {
        var scores = tokens.stream()
                .map(this::scoreEntry)
                .sorted()
                .collect(Collectors.toUnmodifiableList());
        var middleIndex = (scores.size() / 2);
        return scores.get(middleIndex);
    }

    private long scoreEntry(List<Token> tokens) {
        var score = 0L;
        for (var token : tokens) {
            score = score * 5 + scoreForBrackets.get(token.getCharacter());
        }
        return score;
    }
}
