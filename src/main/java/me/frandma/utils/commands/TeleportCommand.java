package me.frandma.utils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0 :
                return false;
            case 1 :
                if (!(sender instanceof Player)) return true;
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) return false;
                Player p = (Player) sender;
                p.teleport(target.getLocation());
                sender.sendMessage("Teleported " + tACC(p.getDisplayName()) + " §fto " + tACC(p.getDisplayName()) + "§f.");
                return true;
            case 2 :
                Player target1 = Bukkit.getPlayer(args[0]);
                Player target2 = Bukkit.getPlayer(args[1]);
                if (target1 == null || target2 == null) return false;
                target1.teleport(target2.getLocation());
                sender.sendMessage("Teleported " + tACC(target1.getDisplayName()) + " §fto " + tACC(target2.getDisplayName()) + "§f.");
                return true;
            default:
                break;
        }
        return true;
    }
    private String tACC(String tTT) {
        return ChatColor.translateAlternateColorCodes('&', tTT);
    }
}
