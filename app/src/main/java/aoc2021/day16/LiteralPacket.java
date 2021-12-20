package aoc2021.day16;

public final class LiteralPacket implements Packet {
    public final static int LITERAL_ID = 4;
    private final int version;
    private final long value;

    public LiteralPacket(int version, long value) {
        this.version = version;
        this.value = value;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public int getOperatorId() {
        return 4;
    }

    @Override
    public <T> T accept(PacketVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public long getValue() {
        return value;
    }
}
