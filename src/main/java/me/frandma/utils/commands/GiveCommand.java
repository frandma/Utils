package me.frandma.utils.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p;
        switch (args.length) {
            case 1 :
                if (!(sender instanceof Player)) return false;
                p = (Player) sender;
                givePlayerItemFromString(sender, p, 1, args[0]);
                return true;
            case 2 :
                if (!(sender instanceof Player)) return false;
                p = (Player) sender;
                if (!isInteger(args[1])) return false;
                givePlayerItemFromString(sender, p, Integer.parseInt(args[1]), args[0]);
                return true;
            case 3 :
                if (!isInteger(args[2])) return false;
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) return false;
                givePlayerItemFromString(sender, target, Integer.parseInt(args[2]), args[1]);
        }
        return true;
    }
    void givePlayerItemFromString(CommandSender sender, Player target, int amount, String s) {
        Material material;
        ItemStack item;
        if (s.contains(":")) {
            String[] split = s.split(":");
            if (!isShort(split[1])) {
                sender.sendMessage("§cInvalid item id.");
                return;
            }
            material = Material.getMaterial(split[0].toUpperCase());
            if (material == null) {
                sender.sendMessage("§cInvalid material.");
                return;
            }
            item = new ItemStack(material, amount, Short.parseShort(split[1]));
        } else {
            material = Material.getMaterial(s.toUpperCase());
            if (material == null) {
                sender.sendMessage("§cInvalid material.");
                return;
            }
            item = new ItemStack(material);
        }
        target.getInventory().addItem(item);
        sender.sendMessage("Given §b" + amount + " §fof §b" + material + " §fto " + target.getDisplayName() + "§f.");
    }
    boolean isInteger(String i) {
        try {
            Integer.parseInt(i);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    boolean isShort(String i) {
        try {
            Short.parseShort(i);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
