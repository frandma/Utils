package me.frandma.utils.listeners;

import me.frandma.utils.Utils;
import me.frandma.utils.user.User;
import me.frandma.utils.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        UserData userData = Utils.instance.getUserData();
        User user = userData.get(p);
        Player player1 = Bukkit.getPlayer(user.getPlayer().getUniqueId());
        if (player1 == null) {
            userData.removePlayer(user.getPlayer());
            Utils.instance.getUserData().removePlayer(user.getPlayer());
        }
        if (p.hasPermission("utils.staff")) {
            e.setQuitMessage(null);
            Bukkit.getServer().broadcast("§9[S] " + p.getDisplayName() + " §fquit.", "utils.staff");
        } else {
            e.setQuitMessage(p.getDisplayName() + " §fquit.");
        }
    }
}
