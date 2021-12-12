package aoc2021.day10;

import java.util.List;
import java.util.stream.Collectors;

public class ErrorScorer {
    public List<Integer> score(List<Token> invalidTokens) {
        return invalidTokens.stream()
                .map(this::scoreFor)
                .collect(Collectors.toUnmodifiableList());
    }

    private int scoreFor(Token token) {
        switch (token.getCharacter()) {
            case ')':
                return 3;
            case ']':
                return 57;
            case '}':
                return 1197;
            case '>':
                return 25137;
            default:
                throw new IllegalArgumentException("Invalid token " + token.getCharacter());
        }
    }
}
