package me.frandma.utils.listeners;

import me.frandma.utils.Utils;
import me.frandma.utils.commands.MuteChatCommand;
import me.frandma.utils.commands.StaffChatCommand;
import me.frandma.utils.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    private final Utils plugin;
    public PlayerChatListener(Utils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (MuteChatCommand.isChatMuted()) {
            if (p.hasPermission("utils.mutechat")) return;
            e.setCancelled(true);
            p.sendMessage("§7You cannot chat while the chat is muted.");
            return;
        }
        User user = plugin.getUserData().get(p);
        if (user.isMuted()) {
            p.sendMessage("§7You cannot chat while muted.");
            e.setCancelled(true);
            return;
        }
        e.setFormat("<" + ChatColor.translateAlternateColorCodes('&', p.getDisplayName()) + ChatColor.WHITE + ">: " + e.getMessage());
        if (!p.hasPermission("utils.staffchat")) return;
        if (e.getMessage().startsWith("#")) {
            e.setCancelled(true);
            String message = e.getMessage().replace("#", "").trim();
            message = "§9[SC] §f<" + p.getDisplayName() + "§f>: " + message;
            Bukkit.getServer().broadcast(message, "utils.staffchat");
        } else {
            if (StaffChatCommand.staffchat.get(p.getUniqueId()) == null) return;
            if (StaffChatCommand.staffchat.get(p.getUniqueId())) {
                e.setCancelled(true);
                String message;
                if (e.getMessage().startsWith("#")) {
                    message = e.getMessage().replace("#", "").trim();
                } else {
                    message = e.getMessage();
                }
                message = "§9[SC] §f<" + p.getDisplayName() + "§f>: " + message;
                Bukkit.getServer().broadcast(message, "utils.staffchat");
            }
        }
    }
}
