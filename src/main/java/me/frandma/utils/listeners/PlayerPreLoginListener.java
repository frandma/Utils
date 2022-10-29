package me.frandma.utils.listeners;

import me.frandma.utils.Utils;
import me.frandma.utils.user.User;
import me.frandma.utils.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerPreLoginListener implements Listener {

    private final Utils plugin;
    public PlayerPreLoginListener(Utils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerPreLoginEvent(AsyncPlayerPreLoginEvent e) {
        UserData userData = plugin.getUserData();
        userData.addPlayer(e.getName(), e.getUniqueId());
        User user = userData.get(e.getUniqueId());
        try {
            user.load();
        } catch (Exception exception) {
            Bukkit.getLogger().info("Failed to load profile for " + e.getName());
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            e.setKickMessage("§cFailed to load user data, if this error persists contact us. \n https://discord.gg/wRGsrJE97R");
            exception.printStackTrace();
            return;
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            Player player1 = Bukkit.getPlayer(e.getUniqueId());
            if (player1 == null)
                userData.removePlayer(e.getUniqueId());
        }, 400L);
    }
}
