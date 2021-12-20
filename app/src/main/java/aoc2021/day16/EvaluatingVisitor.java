package aoc2021.day16;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class EvaluatingVisitor implements PacketVisitor<BigInteger> {
    @Override
    public BigInteger visit(LiteralPacket literalPacket) {
        return BigInteger.valueOf(literalPacket.getValue());
    }

    @Override
    public BigInteger visit(OperatorPacket operatorPacket) {
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

    private BigInteger sum(List<Packet> packets) {
        var result = BigInteger.ZERO;
        for (var packet : packets)
            result = result.add(packet.accept(this));
        return result;
    }

    private BigInteger product(List<Packet> packets) {
        var result = BigInteger.ONE;
        for (var packet : packets)
            result = result.multiply(packet.accept(this));
        return result;
    }

    private BigInteger minimum(List<Packet> packets) {
        return packets.stream().map(packet -> packet.accept(this)).min(Comparator.naturalOrder()).get();
    }

    private BigInteger maximum(List<Packet> packets) {
        return packets.stream().map(packet -> packet.accept(this)).max(Comparator.naturalOrder()).get();
    }

    private BigInteger greaterThan(List<Packet> packets) {
        var fist = packets.get(0).accept(this);
        var second = packets.get(1).accept(this);
        return fist.compareTo(second) > 0 ? BigInteger.ONE : BigInteger.ZERO;
    }

    private BigInteger lessThan(List<Packet> packets) {
        var fist = packets.get(0).accept(this);
        var second = packets.get(1).accept(this);
        return fist.compareTo(second) < 0 ? BigInteger.ONE : BigInteger.ZERO;
    }

    private BigInteger equalTo(List<Packet> packets) {
        var fist = packets.get(0).accept(this);
        var second = packets.get(1).accept(this);
        return Objects.equals(fist, second) ? BigInteger.ONE : BigInteger.ZERO;
    }
}
