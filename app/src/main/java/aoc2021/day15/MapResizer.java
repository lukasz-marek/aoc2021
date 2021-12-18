package aoc2021.day15;

public class MapResizer {
    public int[][] resize(int[][] map, int times) {
        var output = new int[map.length * times][];
        for (var inputX = 0; inputX < map.length; inputX++)
            for (var xMultiplier = 0; xMultiplier < times; xMultiplier++) {
                var xOffset = map.length * xMultiplier;
                output[inputX + xOffset] = new int[map[inputX].length * times];
                for (var inputY = 0; inputY < map[inputX].length; inputY++)
                    for (var yMultiplier = 0; yMultiplier < times; yMultiplier++) {
                        var yOffset = map[inputX].length * yMultiplier;
                        int newValue = computeNewValue(xMultiplier, inputY, yMultiplier, map[inputX]);
                        output[inputX + xOffset][inputY + yOffset] = newValue;
                    }
            }
        return output;
    }

    private int computeNewValue(int xMultiplier, int inputY, int yMultiplier, int[] map) {
        var newValue = (map[inputY] + xMultiplier + yMultiplier);
        while (newValue > 9)
            newValue -= 9;
        return newValue;
    }
}
