package aoc2021.day10;

import java.util.*;
import java.util.stream.Collectors;

public class AutoCompleter {
    private final Set<Character> openings = Set.of('{', '[', '(', '<');
    private final Map<Character, Character> openingClosings = Map.of('{', '}', '[', ']', '(', ')', '<', '>');

    public List<List<Token>> detectMissingTokens(List<Chunk> chunks) {
        return chunks.stream()
                .map(this::findNotClosedBrackets)
                .filter(deque -> !deque.isEmpty())
                .map(this::fillMissingTokens)
                .collect(Collectors.toUnmodifiableList());
    }

    private Deque<Token> findNotClosedBrackets(Chunk chunk) {
        var stack = new ArrayDeque<Token>(chunk.getTokens().size());
        for (var token : chunk.getTokens()) {
            if (isOpening(token))
                stack.push(token);
            else {
                Optional<Token> corruptedToken = detectCorruption(stack, token);
                if (corruptedToken.isPresent())
                    return new ArrayDeque<>();
            }
        }
        return stack;
    }

    private Optional<Token> detectCorruption(ArrayDeque<Token> stack, Token closingToken) {
        var last = stack.pop();
        if (!isValidClosing(last, closingToken))
            return Optional.of(closingToken);
        return Optional.empty();
    }

    private List<Token> fillMissingTokens(Deque<Token> tokens) {
        var missingTokens = new ArrayList<Token>();
        for (var token : tokens)
            missingTokens.add(new Token(openingClosings.get(token.getCharacter())));
        return missingTokens;
    }

    private boolean isOpening(Token token) {
        return openings.contains(token.getCharacter());
    }

    private boolean isValidClosing(Token opening, Token closing) {
        return openingClosings.get(opening.getCharacter()).equals(closing.getCharacter());
    }
}
