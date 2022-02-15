package aoc2021.day18;

import aoc2021.common.InputLoader;

public class Solver {
    private final static SplittingVisitor SPLITTER = new SplittingVisitor();
    private final static ExplodingVisitor EXPLODER = new ExplodingVisitor();
    private final static InputLoader LOADER = new InputLoader();
    private final static NodeParser PARSER = new NodeParser();
    private final static MagnitudeComputingVisitor COMPUTING_VISITOR = new MagnitudeComputingVisitor();

    public long solvePart1() {
        var rawData = LOADER.loadInput("day18.txt");
        var pairs = rawData.stream().map(PARSER::parse).toList();
        var resultNode = pairs.stream().reduce(this::add).orElseThrow();
        return resultNode.accept(COMPUTING_VISITOR);
    }

    private Node add(Node first, Node second) {
        var sum = Pair.of(first, second);

        var shouldContinue = true;
        while (shouldContinue)
            shouldContinue = sum.accept(EXPLODER) || sum.accept(SPLITTER);

        return sum;
    }
}
