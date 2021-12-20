package aoc2021.day16;

public final class LiteralPacket implements Packet {
    private final int version;
    private final int value;

    public LiteralPacket(int version, int value) {
        this.version = version;
        this.value = value;
    }
}
