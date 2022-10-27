package me.frandma.utils.commands;
import me.frandma.utils.other.View;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ViewCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (args.length == 0) return false;
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) return false;
        Player viewer = (Player) sender;
        View.addViewer(viewer, target);
        viewer.openInventory(View.formatInventory(target.getInventory()));
        return true;
    }
}
