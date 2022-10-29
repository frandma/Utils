package me.frandma.utils.listeners;

import me.frandma.utils.Utils;
import me.frandma.utils.user.User;
import me.frandma.utils.user.UserData;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {

    private final Utils plugin;
    public PlayerLoginListener(Utils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        UserData userData = plugin.getUserData();
        User user = userData.get(e.getPlayer());
        if (user == null) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Couldn't fetch user data at the login stage, please try logging in again. If this error persists contact us. \n https://discord.gg/wRGsrJE97R");
            return;
        }
        if (user.isBanned()) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("banMessage")));
        }
    }
}
