package orgllk.partyf;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FMsgCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        if (args.length < 2) return false;
        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("§cPlayer not found.");
            return true;
        }
        if (!FriendManager.areFriends(player.getUniqueId(), target.getUniqueId())) {
            player.sendMessage("§cThat player is not your friend!");
            return true;
        }
        String msg = String.join(" ", args).substring(args[0].length()).trim();
        target.sendMessage("§b[FMSG] " + player.getName() + ": §f" + msg);
        player.sendMessage("§b[FMSG] to " + target.getName() + ": §f" + msg);
        return true;
    }
}