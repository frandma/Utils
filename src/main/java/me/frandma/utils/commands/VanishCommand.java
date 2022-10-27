package me.frandma.utils.commands;

import me.frandma.utils.other.Vanish;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0 :
                if (!(sender instanceof Player)) return false;
                Player p = (Player) sender;
                if (!Vanish.isVanished(p)) {
                    Vanish.vanish(p, true);
                } else {
                    Vanish.unvanish(p);
                }
                return true;
            case 1 :
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) return false;
                if (target.hasPermission("utils.staff")) {
                    if (!Vanish.isVanished(target)) {
                        Vanish.vanish(target, true);
                    } else {
                        Vanish.unvanish(target);
                    }
                } else {
                    sender.sendMessage(target.getDisplayName() + " §fcannot be vanished.");
                }
                return true;
        }
        return true;
    }
}
