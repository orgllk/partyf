package orgllk.partyf;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        MySQLManager.connect(this);
        PartyManager.init(this);
        FriendManager.init(this);

        getCommand("party").setExecutor(new PartyCommand());
        getCommand("friend").setExecutor(new FriendCommand());
        getCommand("fmsg").setExecutor(new FMsgCommand());

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "partyf:bungee");
        getServer().getMessenger().registerIncomingPluginChannel(this, "partyf:bungee", new BungeeMessageListener());

        getLogger().info("PartyF enabled!");
    }

    @Override
    public void onDisable() {
        MySQLManager.disconnect();
        getLogger().info("PartyF disabled!");
    }

    public static Main getInstance() {
        return instance;
    }
}