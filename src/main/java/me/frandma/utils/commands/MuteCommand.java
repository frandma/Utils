package me.frandma.utils.commands;

import me.frandma.utils.Utils;
import me.frandma.utils.user.PlayersDB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class MuteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!label.contains("un")) {
            //Mute Command:
            if (args.length < 2) return false;

            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (target == null) return false;



            long time = new Date().getTime() + Integer.parseInt(args[1]);
            PlayersDB.setMuted(target.getUniqueId(), true, args[2]);

            String name = sender.getName();
            if (!(sender instanceof Player)) name = "Console";

            Bukkit.broadcast("§9[S] §b" + name + "§f muted §b" + target.getName() + "§f with reason §b\"" + args[1] + "\"§f.", "Utils.staff");

        } else {
            //UnMute Command:
            if (args.length < 1) {
                sender.sendMessage("§c/unmute <player>");
                return true;
            }
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§c/unmute <player>");
                return true;
            }



            PlayersDB.setMuted(target.getUniqueId(), false, null);

            String name = sender.getName();
            if (!(sender instanceof Player)) name = "Console";

            Bukkit.broadcast("§9[S] §b" + name + "§f unmuted §b" + target.getName() + "§f.", "Utils.staff");
        }
        return true;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}
