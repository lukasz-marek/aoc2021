package aoc2021.day16;

import java.util.*;

public class PacketParser {
    private final int VERSION_LENGTH = 3;
    private final int TYPE_ID_LENGTH = 3;
    private final int LITERAL_GROUP_LENGTH = 5;
    private final int LENGTH_TYPE_ID_LENGTH = 1;
    private final int PACKETS_LENGTH_LENGTH = 15;
    private final int PACKETS_NUMBER_LENGTH = 11;
    private final int LITERAL_PACKET_TYPE_ID = 4;

    public Packet parse(String binaryPacket) {
        var bits = toBitQueue(binaryPacket);
        return parsePacket(bits);
    }

    private Packet parsePacket(Queue<Character> bits) {
        var version = nextPacketVersion(bits);
        var typeId = nextPacketTypeId(bits);
        if (typeId == LITERAL_PACKET_TYPE_ID)
            return parseLiteral(version, bits);
        else
            return parseOperator(version, typeId, bits);
    }

    private OperatorPacket parseOperator(int version, int typeId, Queue<Character> bits) {
        var lengthTypeId = nextLengthTypeId(bits);
        var containedPackets = new ArrayList<Packet>();
        var consumedBits = VERSION_LENGTH + TYPE_ID_LENGTH + LENGTH_TYPE_ID_LENGTH;
        if (lengthTypeId == 0) {
            consumedBits += PACKETS_LENGTH_LENGTH;
            containedPackets.addAll(parseContainedPacketsByLength(bits));
        } else if (lengthTypeId == 1) {
            consumedBits += PACKETS_NUMBER_LENGTH;
            containedPackets.addAll(parseContainedPacketsByNumber(bits));
        }
        skipTrailingZeroes(consumedBits, bits);
        return new OperatorPacket(version, typeId, containedPackets);
    }

    private List<Packet> parseContainedPacketsByNumber(Queue<Character> bits) {
        var containedPackets = new ArrayList<Packet>();
        var totalCount = getContainedPacketsNumber(bits);
        for (var i = 0; i < totalCount; i++)
            containedPackets.add(parsePacket(bits));
        return containedPackets;
    }

    private List<Packet> parseContainedPacketsByLength(Queue<Character> bits) {
        var containedPackets = new ArrayList<Packet>();
        var totalLength = getContainedPacketsLength(bits);
        var sizeAfterParsing = bits.size() - totalLength;
        while (bits.size() > sizeAfterParsing)
            containedPackets.add(parsePacket(bits));
        return containedPackets;
    }

    private LiteralPacket parseLiteral(int version, Queue<Character> bits) {
        var byteBuffer = new StringBuilder();
        var isLast = false;
        var consumedBits = VERSION_LENGTH + TYPE_ID_LENGTH;
        do {
            var nextGroup = nextLiteralGroup(bits);
            byteBuffer.append(getGroupValue(nextGroup));
            consumedBits += LITERAL_GROUP_LENGTH;
            isLast = isLastLiteralGroup(nextGroup);
        } while (!isLast);
        skipTrailingZeroes(consumedBits, bits);

        var encodedLiteral = byteBuffer.toString();
        var literalValue = Integer.parseInt(encodedLiteral, 2);

        return new LiteralPacket(version, literalValue);
    }

    private int getContainedPacketsLength(Queue<Character> bits) {
        var takenBits = takeBits(bits, PACKETS_LENGTH_LENGTH);
        var binary = takenBits.toString();
        return Integer.parseInt(binary, 2);
    }

    private int getContainedPacketsNumber(Queue<Character> bits) {
        var takenBits = takeBits(bits, PACKETS_NUMBER_LENGTH);
        var binary = takenBits.toString();
        return Integer.parseInt(binary, 2);
    }

    private void skipTrailingZeroes(int consumedBits, Queue<Character> bits) {
        var skipped = 0;
        while ((skipped + consumedBits) % 4 != 0) {
            bits.remove();
            skipped++;
        }
    }

    private boolean isLastLiteralGroup(String group) {
        return group.startsWith("0");
    }

    private String getGroupValue(String group) {
        return group.substring(1);
    }

    private int nextPacketVersion(Queue<Character> bits) {
        var takenBits = takeBits(bits, VERSION_LENGTH);
        var binary = takenBits.toString();
        return Integer.parseInt(binary, 2);
    }

    private int nextPacketTypeId(Queue<Character> bits) {
        var takenBits = takeBits(bits, TYPE_ID_LENGTH);
        var binary = takenBits.toString();
        return Integer.parseInt(binary, 2);
    }

    private int nextLengthTypeId(Queue<Character> bits) {
        var takenBits = takeBits(bits, LENGTH_TYPE_ID_LENGTH);
        var binary = takenBits.toString();
        return Integer.parseInt(binary, 2);
    }

    private String nextLiteralGroup(Queue<Character> bits) {
        var takenBits = takeBits(bits, LITERAL_GROUP_LENGTH);
        return takenBits.toString();
    }

    private Deque<Character> toBitQueue(String binaryPacket) {
        var queue = new ArrayDeque<Character>();
        for (var character : binaryPacket.toCharArray())
            queue.add(character);
        return queue;
    }

    private StringBuilder takeBits(Queue<Character> bits, int numberToTake) {
        var binaryBuffer = new StringBuilder();
        for (var i = 0; i < numberToTake; i++)
            binaryBuffer.append(bits.poll());
        return binaryBuffer;
    }
}
