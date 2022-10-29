package me.frandma.utils.commands;

import me.frandma.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) return true;
            Player p = (Player) sender;
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.sendMessage("Cleared inventory.");
        } else {
            if (!sender.hasPermission("utils.clearothers")) return false;
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage("Invalid target.");
                return true;
            }
            target.getInventory().clear();
            target.getInventory().setArmorContents(null);
            sender.sendMessage(target.getDisplayName() + "'s §finventory has been cleared.");
        }
        return true;
    }
}
