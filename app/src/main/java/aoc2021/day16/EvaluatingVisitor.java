package aoc2021.day16;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class EvaluatingVisitor implements PacketVisitor<Long> {
    @Override
    public Long visit(LiteralPacket literalPacket) {
        return literalPacket.getValue();
    }

    @Override
    public Long visit(OperatorPacket operatorPacket) {
        switch (operatorPacket.getOperatorId()) {
            case 0:
                return sum(operatorPacket.getContainedPackets());
            case 1:
                return product(operatorPacket.getContainedPackets());
            case 2:
                return minimum(operatorPacket.getContainedPackets());
            case 3:
                return maximum(operatorPacket.getContainedPackets());
            case 5:
                return greaterThan(operatorPacket.getContainedPackets());
            case 6:
                return lessThan(operatorPacket.getContainedPackets());
            case 7:
                return equalTo(operatorPacket.getContainedPackets());
            default:
                throw new IllegalArgumentException("Invalid operatorId: " + operatorPacket.getOperatorId());
        }
    }

    private long sum(List<Packet> packets) {
        return packets.stream().map(packet -> packet.accept(this)).mapToLong(Long::longValue).sum();
    }

    private long product(List<Packet> packets) {
        var result = 1;
        for (var packet : packets)
            result *= packet.accept(this);
        return result;
    }

    private long minimum(List<Packet> packets) {
        return packets.stream().map(packet -> packet.accept(this)).min(Comparator.naturalOrder()).get();
    }

    private long maximum(List<Packet> packets) {
        return packets.stream().map(packet -> packet.accept(this)).max(Comparator.naturalOrder()).get();
    }

    private long greaterThan(List<Packet> packets) {
        var fist = packets.get(0).accept(this);
        var second = packets.get(1).accept(this);
        return fist > second ? 1 : 0;
    }

    private long lessThan(List<Packet> packets) {
        var fist = packets.get(0).accept(this);
        var second = packets.get(1).accept(this);
        return fist < second ? 1 : 0;
    }

    private long equalTo(List<Packet> packets) {
        var fist = packets.get(0).accept(this);
        var second = packets.get(1).accept(this);
        return Objects.equals(fist, second) ? 1 : 0;
    }
}
