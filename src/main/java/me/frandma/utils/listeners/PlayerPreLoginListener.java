package me.frandma.utils.listeners;

import me.frandma.utils.Utils;
import me.frandma.utils.user.User;
import me.frandma.utils.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.Plugin;

public class PlayerPreLoginListener implements Listener {
    @EventHandler
    public void onPlayerPreLoginEvent(AsyncPlayerPreLoginEvent e) {
        Player player = Bukkit.getPlayer(e.getUniqueId());
        if (player != null && player.isOnline()) {
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            e.setKickMessage("§cYou are already online.");
            return;
        }
        UserData userData = Utils.instance.getUserData();
        userData.addPlayer(player);
        User user = userData.get(player);
        try {
            user.load();
        } catch (Exception exception) {
            Bukkit.getLogger().info("Failed to load profile for " + player.getName());
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            e.setKickMessage("§cFailed to load user data, if this error persists contact us. \n https://discord.gg/wRGsrJE97R");
            exception.printStackTrace();
            return;
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)Utils.instance, () -> {
            Player player1 = Bukkit.getPlayer(e.getUniqueId());
            if (player1 == null)
                userData.removePlayer(player);
        }, 400L);
    }
}
