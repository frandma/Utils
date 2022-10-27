package me.frandma.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.frandma.utils.other.Vanish;
import org.bukkit.OfflinePlayer;

public class PAPI extends PlaceholderExpansion {

    private final Utils plugin;

    public PAPI(Utils plugin) {
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
