package me.frandma.utils.commands;
import me.frandma.utils.other.View;
import me.frandma.utils.user.CustomHolder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class ViewCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (args.length == 0) return false;
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) return false;
        Player senderPlayer = (Player) sender;
        Inventory inv = Bukkit.createInventory(new CustomHolder(senderPlayer, targetPlayer), 5*9, targetPlayer.getName() + "'s inventory");
        inv.setContents(View.formatInventory(targetPlayer.getInventory()).getContents());
        senderPlayer.openInventory(inv);
        return true;
    }
}
