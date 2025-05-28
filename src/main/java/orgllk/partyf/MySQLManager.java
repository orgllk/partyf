package orgllk.partyf;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLManager {
    private static Connection connection;

    public static void connect(JavaPlugin plugin) {
        FileConfiguration config = plugin.getConfig();
        String host = config.getString("mysql.host");
        int port = config.getInt("mysql.port");
        String db = config.getString("mysql.database");
        String user = config.getString("mysql.user");
        String pass = config.getString("mysql.password");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false";
        try {
            connection = DriverManager.getConnection(url, user, pass);
            plugin.getLogger().info("Connected to MySQL!");
        } catch (SQLException e) {
            plugin.getLogger().severe("Could not connect to MySQL: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) { }
        }
    }
}