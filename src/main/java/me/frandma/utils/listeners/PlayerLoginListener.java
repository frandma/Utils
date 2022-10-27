package me.frandma.utils.listeners;

import me.frandma.utils.Utils;
import me.frandma.utils.user.User;
import me.frandma.utils.user.UserData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        UserData userData = Utils.instance.getUserData();
        User user = userData.get(e.getPlayer());
        if (user == null) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Couldn't fetch user data at the login stage, please try logging in again. If this error persists contact us. \n https://discord.gg/wRGsrJE97R");
            return;
        }
        if (user.isBanned()) {
            user.getPlayer().kickPlayer(Utils.instance.getConfig().getString("banMessage"));
        }
    }
}
