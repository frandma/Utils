package me.frandma.utils.commands;

import me.frandma.utils.Utils;
import me.frandma.utils.user.PlayersDB;
import me.frandma.utils.user.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {

    private final Utils plugin;
    public BanCommand(Utils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!label.contains("un")) {
            //Ban Command:
            if (args.length < 2) return false;

            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (target == null) return false;

            if (target.isOnline()) {
                User user = plugin.getUserData().get(target.getPlayer());
                user.ban(args[1]);
            } else {
                PlayersDB.setBanned(target.getUniqueId(), true, args[1]);
            }

            String name = sender.getName();
            if (!(sender instanceof Player)) name = "Console";

            Bukkit.broadcast("§9[S] §b" + name + "§f banned §b" + target.getName() + "§f with reason §b\"" + args[1] + "\"§f.", "Utils.staff");

        } else {
            //UnBan Command:
            if (args.length < 1) {
                sender.sendMessage("§c/unban <player>");
                return true;
            }
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§c/unban <player>");
                return true;
            }

            PlayersDB.setBanned(target.getUniqueId(), false, null);

            String name = sender.getName();
            if (!(sender instanceof Player)) name = "Console";

            Bukkit.broadcast("§9[S] §b" + name + "§f unbanned §b" + target.getName() + "§f.", "Utils.staff");
        }
        return true;
    }
}
