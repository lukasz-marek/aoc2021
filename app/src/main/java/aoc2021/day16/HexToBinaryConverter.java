package aoc2021.day16;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class HexToBinaryConverter {
    private final static Map<Character, String> mapping = ImmutableMap.<Character, String>builder()
            .put('0', "0000")
            .put('1', "0001")
            .put('2', "0010")
            .put('3', "0011")
            .put('4', "0100")
            .put('5', "0101")
            .put('6', "0110")
            .put('7', "0111")
            .put('8', "1000")
            .put('9', "1001")
            .put('A', "1010")
            .put('B', "1011")
            .put('C', "1100")
            .put('D', "1101")
            .put('E', "1110")
            .put('F', "1111")
            .build();

    public String convertToBinary(String hex) {
        var output = new StringBuilder();
        for (var hexDigit : hex.toCharArray())
            output.append(mapping.get(hexDigit));
        return output.toString();
    }
}
