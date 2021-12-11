package aoc2021.day9;

import java.util.Set;

public final class Basin {
    private final Set<Coordinates> members;

    public Basin(Set<Coordinates> members) {
        this.members = Set.copyOf(members);
    }

    public Set<Coordinates> getMembers() {
        return members;
    }
}
