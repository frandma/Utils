package me.frandma.utils.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) return false;
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) return false;
        String name = sender.getName();
        if (!(sender instanceof Player)) name = "Console";
        if (target.getName() == name) return false;
        sender.sendMessage("(To " + target.getDisplayName() + "§f: " + args[1] + ")");
        target.sendMessage("(From " + name + "§f: " + args[1] + ")");
        return true;
    }
}
