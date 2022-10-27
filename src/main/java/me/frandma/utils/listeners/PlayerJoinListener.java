package me.frandma.utils.listeners;

import me.frandma.utils.Utils;
import me.frandma.utils.other.Vanish;
import me.frandma.utils.user.User;
import me.frandma.utils.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UserData userData = Utils.instance.getUserData();
        User user = userData.get(p);
        if (user == null) {
            p.kickPlayer("Couldn't fetch user data at the join stage, please try logging in again. If this error persists contact us. \n https://discord.gg/wRGsrJE97R");
            return;
        }
        p.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.getChat().getPlayerPrefix(p)) + p.getName());
        if (p.hasPermission("utils.staff")) {
            e.setJoinMessage(null);
            Vanish.vanish(p, false);
            Bukkit.getServer().broadcast("§9[S] " + p.getDisplayName() + " §fjoined.", "utils.staff");
        } else {
            e.setJoinMessage(p.getDisplayName() + " §fjoined.");
            for (UUID vanishedplayer : Vanish.vanishlist) {
                p.hidePlayer(Bukkit.getPlayer(vanishedplayer));
            }
        }
    }
}
