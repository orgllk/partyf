package orgllk.partyf;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("§e/party invite <player>, /party leave, /p c (toggle chat)");
            return true;
        }
        if (args[0].equalsIgnoreCase("invite") && args.length > 1) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage("§cPlayer not found.");
                return true;
            }
            PartyManager.invite(player, target);
            return true;
        }
        if (args[0].equalsIgnoreCase("leave")) {
            PartyManager.leave(player);
            return true;
        }
        if (args[0].equalsIgnoreCase("c")) {
            PartyManager.togglePartyChat(player.getUniqueId());
            player.sendMessage(PartyManager.isPartyChatToggled(player.getUniqueId())
                    ? "§bYou are now talking in party chat."
                    : "§eYou are now talking in public chat.");
            return true;
        }
        return false;
    }
}