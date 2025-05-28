package orgllk.partyf;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FriendCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (args.length == 0) {
            FriendGUI.open(player);
            return true;
        }
        if (args[0].equalsIgnoreCase("add") && args.length > 1) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage("§cPlayer not found.");
                return true;
            }
            FriendManager.addFriend(player.getUniqueId(), target.getUniqueId());
            player.sendMessage("§aAdded " + target.getName() + " as a friend.");
            target.sendMessage("§a" + player.getName() + " added you as a friend!");
            return true;
        }
        if (args[0].equalsIgnoreCase("remove") && args.length > 1) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage("§cPlayer not found.");
                return true;
            }
            FriendManager.removeFriend(player.getUniqueId(), target.getUniqueId());
            player.sendMessage("§eRemoved " + target.getName() + " from your friends.");
            return true;
        }
        return false;
    }
}