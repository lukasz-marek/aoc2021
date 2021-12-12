package aoc2021.day10;

import java.util.List;

public final class Chunk {
    private final List<Token> tokens;

    public Chunk(List<Token> tokens) {
        this.tokens = List.copyOf(tokens);
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
