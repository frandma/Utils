package me.frandma.utils.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SudoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) return false;
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) return false;
        String message = String.join(" ", args);
        message = message.replaceFirst(args[0] + " ", "");
        target.chat(message);
        return true;
    }
}
