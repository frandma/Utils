package me.frandma.utils.commands;

import me.frandma.utils.UtilsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MuteChatCommand implements CommandExecutor {

    private final UtilsPlugin plugin;
    public MuteChatCommand(UtilsPlugin plugin) {
        this.plugin = plugin;
    }

    private static boolean chatMuted;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        chatMuted = !chatMuted;
        Bukkit.broadcastMessage(chatMuted ? plugin.getConfig().getString("chatMuted") : plugin.getConfig().getString("chatUnMuted"));
        return true;
    }

    public static Boolean isChatMuted() {
        return chatMuted;
    }
}
