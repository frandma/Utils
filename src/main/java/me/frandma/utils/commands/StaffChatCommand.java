package me.frandma.utils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class StaffChatCommand implements CommandExecutor {
    public static HashMap<UUID, Boolean> staffchat = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if (args.length > 0) {
            String message = String.join(" ", args).replace("#", "").trim();
            message = "§9[SC] §f<" + ChatColor.translateAlternateColorCodes('&', p.getDisplayName()) + "§f>: " + message;
            Bukkit.getServer().broadcast(message, "utils.staffchat");
        } else {
            staffchat.put(p.getUniqueId(), !staffchat.getOrDefault(p.getUniqueId(), false));
            if (staffchat.get(p.getUniqueId()))
                p.sendMessage("Your messages will be automatically sent to the StaffChat.");
            if (!staffchat.get(p.getUniqueId()))
                p.sendMessage("Your messages will no longer be automatically sent to the StaffChat.");
        }
        return true;
    }
}
