package aoc2021.day10;

import java.util.*;
import java.util.stream.Collectors;

public class CorruptionDetector {
    private final Set<Character> openings = Set.of('{', '[', '(', '<');
    private final Map<Character, Character> openingClosings = Map.of('{', '}', '[', ']', '(', ')', '<', '>');

    public List<Token> detectCorruptions(List<Chunk> chunks) {
        return chunks.stream()
                .map(this::findFirstCorruption)
                .flatMap(Optional::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    private Optional<Token> findFirstCorruption(Chunk chunk) {
        var stack = new ArrayDeque<Token>(chunk.getTokens().size());
        for (var token : chunk.getTokens()) {
            if (isOpening(token))
                stack.push(token);
            else {
                Optional<Token> corruptedToken = detectCorruption(stack, token);
                if (corruptedToken.isPresent())
                    return corruptedToken;
            }
        }
        return Optional.empty();
    }

    private Optional<Token> detectCorruption(ArrayDeque<Token> stack, Token closingToken) {
        var last = stack.pop();
        if (!isValidClosing(last, closingToken))
            return Optional.of(closingToken);
        return Optional.empty();
    }

    private boolean isOpening(Token token) {
        return openings.contains(token.getCharacter());
    }

    private boolean isValidClosing(Token opening, Token closing) {
        return openingClosings.get(opening.getCharacter()).equals(closing.getCharacter());
    }
}
