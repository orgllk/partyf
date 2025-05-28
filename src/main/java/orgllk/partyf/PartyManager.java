package orgllk.partyf;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class PartyManager {
    private static final Map<UUID, Party> playerPartyMap = new HashMap<>();
    private static final Map<UUID, Boolean> partyChatToggled = new HashMap<>();

    public static void init(Main plugin) {
        // Optionally: load parties from DB on startup
    }

    // Party auto-create on invite
    public static void invite(Player inviter, Player target) {
        if (!FriendManager.areFriends(inviter.getUniqueId(), target.getUniqueId())) {
            inviter.sendMessage("§cThat player is not your friend!");
            return;
        }
        Party party = getParty(inviter.getUniqueId());
        if (party == null) {
            party = new Party(inviter.getUniqueId());
            playerPartyMap.put(inviter.getUniqueId(), party);
        }
        party.invite(target.getUniqueId());
        playerPartyMap.put(target.getUniqueId(), party);
        inviter.sendMessage("§aInvited " + target.getName() + " to your party.");
        target.sendMessage("§aYou were invited to a party by " + inviter.getName() + "!");
    }

    public static Party getParty(UUID player) {
        return playerPartyMap.get(player);
    }

    public static void leave(Player player) {
        Party party = getParty(player.getUniqueId());
        if (party != null) {
            party.remove(player.getUniqueId());
            playerPartyMap.remove(player.getUniqueId());
            player.sendMessage("§cYou left the party.");
        }
    }

    public static void togglePartyChat(UUID uuid) {
        boolean toggled = partyChatToggled.getOrDefault(uuid, false);
        partyChatToggled.put(uuid, !toggled);
    }

    public static boolean isPartyChatToggled(UUID uuid) {
        return partyChatToggled.getOrDefault(uuid, false);
    }

    public static List<Player> getOnlinePartyMembers(UUID uuid) {
        Party party = getParty(uuid);
        List<Player> members = new ArrayList<>();
        if (party != null) {
            for (UUID pid : party.getMembers()) {
                Player p = Bukkit.getPlayer(pid);
                if (p != null && p.isOnline()) members.add(p);
            }
        }
        return members;
    }
}