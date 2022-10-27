package me.frandma.utils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TphereCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (args.length == 0) return false;
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) return false;
        Player p = (Player) sender;
        target.teleport(p.getLocation());
        p.sendMessage("Teleported " + tACC(target.getDisplayName()) + " §fto " + tACC(p.getDisplayName()) + "§f.");
        return true;
    }
    private String tACC(String tTT) {
        return ChatColor.translateAlternateColorCodes('&', tTT);
    }
}
