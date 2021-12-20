package aoc2021.day16;

public interface Packet {
    int getVersion();

    int getOperatorId();

    <T> T accept(PacketVisitor<T> visitor);
}
