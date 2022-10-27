package me.frandma.utils.sqlite;

import lombok.experimental.UtilityClass;
import me.frandma.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.*;

@UtilityClass
public class SQL {
    private Connection c;

    public void setup() {
        File dataFolder = new File(Utils.instance.getDataFolder(), "players.db");
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                Utils.instance.getLogger().info("File write error: players.db");
            }
        }
        try {
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
        } catch (SQLException e) {
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void setMuted(OfflinePlayer p, boolean bool, String reason) {
        try (PreparedStatement ps = c.prepareStatement("UPDATE players SET muted = ?, muteReason = ? WHERE uuid = ?;")) {
            ps.setBoolean(1, bool);
            ps.setString(2, reason);
            ps.setString(3, p.getUniqueId().toString());
            ps.executeQuery();
            ps.close();
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public boolean isMuted(OfflinePlayer p) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE uuid = ?")) {
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs.getBoolean(2);
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public String getMuteReason(OfflinePlayer p) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE uuid = ?")) {
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs.getString(3);
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public void setBanned(OfflinePlayer p, boolean bool, String reason) {
        try (PreparedStatement ps = c.prepareStatement("UPDATE players SET banned = ?, banDuration = ?, banReason = ? WHERE uuid = ?;")) {
            ps.setBoolean(1, bool);
            ps.setString(2, reason);
            ps.setString(3, p.getUniqueId().toString());
            ps.executeQuery();
            ps.close();
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
        }
        if (bool == false) return;
        if (!p.isOnline()) return;
        ((Player) p).kickPlayer("§cYou have been banned.");
    }

    public boolean isBanned(OfflinePlayer p) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE uuid = ?")) {
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs.getBoolean(4);
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    public String getBanReason(OfflinePlayer p) {
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE uuid = ?")) {
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs.getString(5);
        } catch (SQLException e){
            Bukkit.getLogger().info(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}
