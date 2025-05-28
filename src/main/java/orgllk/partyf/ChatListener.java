package orgllk.partyf;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;

import java.util.List;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (PartyManager.isPartyChatToggled(player.getUniqueId())) {
            e.setCancelled(true);
            List<Player> members = PartyManager.getOnlinePartyMembers(player.getUniqueId());
            for (Player p : members) {
                p.sendMessage("§3[Party] " + player.getName() + ": §f" + e.getMessage());
            }
        }
    }
}