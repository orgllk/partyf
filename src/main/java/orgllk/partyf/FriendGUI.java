package orgllk.partyf;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.stream.Collectors;

public class FriendGUI {
    public static void open(Player player) {
        Set<UUID> friends = FriendManager.getFriends(player.getUniqueId());
        Inventory inv = Bukkit.createInventory(null, 54, "§bYour Friends");
        int i = 0;
        for (UUID f : friends) {
            Player fp = Bukkit.getPlayer(f);
            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
            ItemMeta meta = skull.getItemMeta();
            meta.setDisplayName((fp != null && fp.isOnline() ? "§a" : "§c") + Bukkit.getOfflinePlayer(f).getName());
            List<String> lore = new ArrayList<>();
            lore.add(fp != null && fp.isOnline() ? "§aOnline" : "§cOffline");
            lore.add("§7Left-click: Message");
            lore.add("§7Right-click: Remove");
            meta.setLore(lore);
            skull.setItemMeta(meta);
            inv.setItem(i++, skull);
        }
        player.openInventory(inv);
    }

    public static void handleClick(Player player, InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals("§bYour Friends")) {
            e.setCancelled(true);
            ItemStack item = e.getCurrentItem();
            if (item == null || !item.hasItemMeta()) return;
            String name = item.getItemMeta().getDisplayName().replace("§a", "").replace("§c", "");
            Player target = Bukkit.getPlayer(name);
            UUID uuid = target != null ? target.getUniqueId() : Bukkit.getOfflinePlayer(name).getUniqueId();

            if (e.isLeftClick()) {
                player.closeInventory();
                player.performCommand("fmsg " + name + " ");
            } else if (e.isRightClick()) {
                FriendManager.removeFriend(player.getUniqueId(), uuid);
                player.sendMessage("§eRemoved " + name + " from your friends.");
                open(player);
            }
        }
    }
}