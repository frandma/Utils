package me.frandma.utils.other;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

@UtilityClass
public class Vanish {
    public ArrayList<UUID> vanishlist = new ArrayList<>();
    public void vanish(Player p, boolean announceToOtherStaff) {
        if (isVanished(p)) return;
        vanishlist.add(p.getUniqueId());
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player == p) continue;
            if (!player.hasPermission("utils.staff")) continue;
            player.hidePlayer(p);
        }
        if (announceToOtherStaff) Bukkit.getServer().broadcast("§9[S] " + tACC(p.getDisplayName()) + " §fvanished.", "utils.staff");
    }

    public void unvanish(Player p) {
        if (!vanishlist.contains(p.getUniqueId())) return;
        vanishlist.remove(p.getUniqueId());
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player == p) continue;
            if (!player.hasPermission("utils.staff")) continue;
            player.showPlayer(p);
        }
        Bukkit.getServer().broadcast("§9[S] " + tACC(p.getDisplayName()) + " §funvanished.", "utils.staff");
    }

    public boolean isVanished(OfflinePlayer player) {
        return vanishlist.contains(player.getUniqueId());
    }
    public boolean isVanished(UUID uuid) {
        return vanishlist.contains(uuid);
    }

    private static String tACC(String tTT) {
        return ChatColor.translateAlternateColorCodes('&', tTT);
    }

}
