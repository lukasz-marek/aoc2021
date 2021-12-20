package aoc2021.day16;

public class VersionAddingVisitor implements PacketVisitor<Integer> {
    @Override
    public Integer visit(LiteralPacket literalPacket) {
        return literalPacket.getVersion();
    }

    @Override
    public Integer visit(OperatorPacket operatorPacket) {
        var selfValue = operatorPacket.getVersion();
        var nestedValue = operatorPacket.getContainedPackets().stream()
                .map(packet -> packet.accept(this))
                .mapToInt(Integer::valueOf)
                .sum();
        return selfValue + nestedValue;
    }
}
