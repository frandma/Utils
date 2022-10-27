package me.frandma.utils.commands;

import me.frandma.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§9" + Utils.instance.getConfig().get("discordLink"));
        return true;
    }
}
