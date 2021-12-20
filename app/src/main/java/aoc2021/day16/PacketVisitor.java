package aoc2021.day16;

public interface PacketVisitor<T> {
    T visit(LiteralPacket literalPacket);
    T visit(OperatorPacket operatorPacket);
}
