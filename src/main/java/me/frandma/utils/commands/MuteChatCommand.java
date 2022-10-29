package me.frandma.utils.commands;

import me.frandma.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MuteChatCommand implements CommandExecutor {

    private final Utils plugin;
    public MuteChatCommand(Utils plugin) {
        this.plugin = plugin;
    }

    private static boolean chatMuted;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        chatMuted = !chatMuted;
        if (chatMuted) {
            Bukkit.broadcastMessage(plugin.getConfig().getString("chatMuted"));
        } else {
            Bukkit.broadcastMessage(plugin.getConfig().getString("chatUnMuted"));
        }
        return true;
    }

    public static Boolean isChatMuted() {
        return chatMuted;
    }
}
