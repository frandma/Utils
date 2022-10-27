package me.frandma.utils.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (label.toLowerCase()) {
            case "gamemode" :
            case "gm" :
                if (args.length == 0) {
                    return false;
                } else if (args.length == 1) {
                    if (!(sender instanceof Player)) return true;
                    Player p = (Player) sender;
                    setGamemode(p, p, args[0]);
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) return false;
                    setGamemode(sender, target, args[0]);
                }
                return true;
            case "gms" :
            case "gmc" :
            case "gma" :
            case "gmsp" :
                if (args.length == 0) {
                    if (!(sender instanceof Player)) return false;
                    Player p = (Player) sender;
                    setGamemode(sender, p, label.toLowerCase());
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) return false;
                    setGamemode(sender, target, label.toLowerCase());
                }
                return true;
            default:
                break;
        }
        return true;
    }
    void setGamemode(CommandSender sender, Player target, String gamemode) {
        switch (gamemode.toLowerCase()) {
            case "gms":
            case "0":
            case "s":
            case "survival":
                target.setGameMode(GameMode.SURVIVAL);
                sender.sendMessage("Set " + target.getDisplayName() + "'s §fgamemode to §bSurvival§f.");
                break;
            case "gmc":
            case "1":
            case "c":
            case "creative":
                target.setGameMode(GameMode.CREATIVE);
                sender.sendMessage("Set " + target.getDisplayName() + "'s §fgamemode to §bCreative§f.");
                break;
            case "gma":
            case "2":
            case "a":
            case "adventure":
                target.setGameMode(GameMode.ADVENTURE);
                sender.sendMessage("Set " + target.getDisplayName() + "'s §fgamemode to §bAdventure§f.");
                break;
            case "gmsp":
            case "3":
            case "sp":
            case "spectator":
                target.setGameMode(GameMode.SPECTATOR);
                sender.sendMessage("Set " + target.getDisplayName() + "'s §fgamemode to §bSpectator§f.");
                break;
            default:
                sender.sendMessage("fai.;ed hahahahaha");
                break;
        }
    }
}
