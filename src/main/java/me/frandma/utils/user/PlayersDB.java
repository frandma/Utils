package me.frandma.utils.user;

import lombok.experimental.UtilityClass;
import me.frandma.utils.UtilsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

@UtilityClass
public class PlayersDB {
    private Connection c;
    private final String afterMuteMessage = UtilsPlugin.getInstance().getConfig().getString("afterMuteMessage");
    private final String unMuteMessage = UtilsPlugin.getInstance().getConfig().getString("afterMuteMessage");

    public void setup() {
        File dataFolder = new File(UtilsPlugin.getInstance().getDataFolder(), "players.db");
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                UtilsPlugin.getInstance().getLogger().info("File write error: players.db");
            }
        }
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            try (PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS players (" +
                    "uuid varchar(255) PRIMARY KEY, " +
                    "muted varchar(255) NOT NULL, " +
                    "muteReason varchar(255) NOT NULL, " +
                    "banned varchar(255) NOT NULL, " +
                    "banReason varchar(255) NOT NULL);")) {
                ps.execute();
            } catch (Exception e) {
                Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
            }
        } catch (SQLException | ClassNotFoundException e) {
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void setMuted(UUID uuid, boolean bool, String reason) {
        try (PreparedStatement ps = c.prepareStatement("UPDATE players SET muted = ?, muteReason = ? WHERE uuid = ?;")) {
            ps.setBoolean(1, bool);
            ps.setString(2, reason);
            ps.setString(3, uuid.toString());
            ps.executeQuery();
            ps.close();
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
        }
        Player p = Bukkit.getPlayer(uuid);
        if (bool) {
            if (!p.isOnline()) return;
            p.sendMessage(afterMuteMessage);
        } else {
            if (!p.isOnline()) return;
            p.sendMessage(unMuteMessage);
        }

    }

    public boolean isMuted(UUID uuid) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE uuid = ?")) { //line 66
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs.getBoolean(2);
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public String getMuteReason(UUID uuid) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE uuid = ?")) {
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs.getString(3);
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public void setBanned(UUID uuid, boolean bool, String reason) {
        try (PreparedStatement ps = c.prepareStatement("UPDATE players SET banned = ?, banDuration = ?, banReason = ? WHERE uuid = ?;")) {
            ps.setBoolean(1, bool);
            ps.setString(2, reason);
            ps.setString(3, uuid.toString());
            ps.executeQuery();
            ps.close();
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
        }
        if (!bool) return;
        Player p = Bukkit.getPlayer(uuid);
        if (!p.isOnline()) return;
        p.kickPlayer(UtilsPlugin.getInstance().getConfig().getString("afterBanMessage"));
    }

    public boolean isBanned(UUID uuid) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE uuid = ?")) {
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs.getBoolean(4);
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    public String getBanReason(UUID uuid) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE uuid = ?")) {
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs.getString(5);
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public ResultSet getResultSet(UUID uuid) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE uuid = ?")) {
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs;
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}
