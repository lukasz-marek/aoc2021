package aoc2021.day16;

import aoc2021.common.InputLoader;

public class Main {
    public static void main(String[] args) {
        var loader = new InputLoader();
        var input = loader.loadInput("day16.txt").get(0);
        var converter = new HexToBinaryConverter();
        var decodedInput = converter.convertToBinary(input);
        var parser = new PacketParser();
        var parsed = parser.parse(decodedInput);

        var versionVisitor = new VersionAddingVisitor();
        System.out.println("Part 1:" + parsed.accept(versionVisitor));

        var evaluatingVisitor = new EvaluatingVisitor();
        System.out.println("Part 2: " + parsed.accept(evaluatingVisitor));
    }
}
