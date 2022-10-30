package me.frandma.utils.other;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.frandma.utils.UtilsPlugin;
import org.bukkit.OfflinePlayer;

public class PAPI extends PlaceholderExpansion {

    private final UtilsPlugin plugin;

    public PAPI(UtilsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "Frandma";
    }

    @Override
    public String getIdentifier() {
        return "Utils";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("vanished")) {
            return String.valueOf(Vanish.isVanished(player));
        }
        return null;
    }
}
