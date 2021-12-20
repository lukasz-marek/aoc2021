package aoc2021.day16;

import java.util.List;

public final class OperatorPacket implements Packet {
    private final int version;
    private final int typeId;
    private final List<Packet> containedPackets;

    public OperatorPacket(int version, int typeId, List<Packet> containedPackets) {
        this.version = version;
        this.typeId = typeId;
        this.containedPackets = List.copyOf(containedPackets);
    }
}
