package orgllk.partyf;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.*;

public class FriendManager {
    private static final Map<UUID, Set<UUID>> friends = new HashMap<>();

    public static void init(Main plugin) {
        // Optionally: Load friends from MySQL
    }

    public static boolean areFriends(UUID uuid1, UUID uuid2) {
        return friends.containsKey(uuid1) && friends.get(uuid1).contains(uuid2);
    }

    public static void addFriend(UUID uuid1, UUID uuid2) {
        friends.computeIfAbsent(uuid1, k -> new HashSet<>()).add(uuid2);
        friends.computeIfAbsent(uuid2, k -> new HashSet<>()).add(uuid1);
    }

    public static void removeFriend(UUID uuid1, UUID uuid2) {
        if (friends.containsKey(uuid1)) friends.get(uuid1).remove(uuid2);
        if (friends.containsKey(uuid2)) friends.get(uuid2).remove(uuid1);
    }

    public static Set<UUID> getFriends(UUID uuid) {
        return friends.getOrDefault(uuid, Collections.emptySet());
    }

    public static boolean isOnline(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        return p != null && p.isOnline();
    }
}