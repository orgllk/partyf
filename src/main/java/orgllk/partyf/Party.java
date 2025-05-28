package orgllk.partyf;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Party {
    private final UUID leader;
    private final Set<UUID> members = new HashSet<>();

    public Party(UUID leader) {
        this.leader = leader;
        members.add(leader);
    }

    public void invite(UUID player) {
        members.add(player);
    }

    public void remove(UUID player) {
        members.remove(player);
    }

    public Set<UUID> getMembers() {
        return members;
    }

    public UUID getLeader() {
        return leader;
    }
}