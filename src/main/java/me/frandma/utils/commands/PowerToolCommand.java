package me.frandma.utils.commands;

import me.frandma.utils.other.PowerTool;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PowerToolCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) return false;
        if (args.length == 0) {
            if (PowerTool.hasPowerTool(p)) {
                PowerTool.setPowerTool(p, null, null);
                p.sendMessage("Cleared PowerTool.");
            } else {
                return false;
            }
        } else {
            String message = String.join(" ", args);
            PowerTool.setPowerTool(p, p.getItemInHand().getType(), message);
            p.sendMessage("Set PowerTool of §b" + p.getItemInHand().getType() + " §fto §b\"" + message + "\" §f.");
        }
        return true;
    }

}
