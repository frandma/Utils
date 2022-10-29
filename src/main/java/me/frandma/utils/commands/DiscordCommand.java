package me.frandma.utils.commands;

import me.frandma.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {

    private final Utils plugin;
    public DiscordCommand(Utils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§9" + plugin.getConfig().get("discordLink"));
        return true;
    }
}
