package orgllk.partyf;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;

public class PlayerListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        // Could sync friend/party states from MySQL here.
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        // Could update state for offline/online here.
    }
}