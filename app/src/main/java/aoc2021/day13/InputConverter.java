package aoc2021.day13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputConverter {
    private final Pattern coordinatesPattern = Pattern.compile("^(\\d+),(\\d+)$");
    private final Pattern instructionPattern = Pattern.compile("^fold along x=(\\d+)$");

    public Problem convert(List<String> input) {
        char[][] paper = parsePaper(input);
        var foldInstructions = parseInstructions(input);

        return new Problem(foldInstructions, paper);
    }

    private List<FoldInstruction> parseInstructions(List<String> input) {
        return input.stream()
                .map(String::trim)
                .dropWhile(line -> !line.isEmpty())
                .skip(1)
                .map(this::convertInstruction)
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
    }

    private char[][] parsePaper(List<String> input) {
        var paperDescription = input.stream()
                .map(String::trim)
                .takeWhile(line -> !line.isEmpty())
                .collect(Collectors.toUnmodifiableList());
        return convertPaper(paperDescription);
    }

    private FoldInstruction convertInstruction(String line) {
        var match = instructionPattern.matcher(line);
        if (match.find()) {
            var by = match.group(1);
            return new FoldVertically(Integer.parseInt(by));
        }
        return null;
    }

    private char[][] convertPaper(List<String> description) {
        var paper = createEmptyPaper(description);
        for (var line : description) {
            paper[getY(line)][getX(line)] = '#';
        }
        return paper;
    }

    private char[][] createEmptyPaper(List<String> description) {
        var maxX = description.stream().map(this::getX).max(Comparator.naturalOrder()).get();
        var maxY = description.stream().map(this::getY).max(Comparator.naturalOrder()).get();
        var paper = new char[maxY + 1][];
        for (var i = 0; i < paper.length; i++) {
            paper[i] = new char[maxX + 1];
            Arrays.fill(paper[i], '-');
        }
        return paper;
    }

    private int getX(String paperLine) {
        var match = coordinatesPattern.matcher(paperLine);
        if (match.find()) {
            return Integer.parseInt(match.group(1));
        }
        return -1;
    }

    private int getY(String paperLine) {
        var match = coordinatesPattern.matcher(paperLine);
        if (match.find()) {
            return Integer.parseInt(match.group(2));
        }
        return -1;
    }
}
