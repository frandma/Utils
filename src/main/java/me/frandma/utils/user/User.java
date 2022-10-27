package me.frandma.utils.user;

import me.frandma.utils.sqlite.SQL;
import org.bukkit.entity.Player;

public class User {
    private Player player;
    private boolean muted;
    private String muteReason;
    private boolean banned;
    private String banReason;

    public User(Player player) {
        this.player = player;
        this.muted = SQL.isMuted(player);
        this.muteReason = SQL.getMuteReason(player);
        this.banned = SQL.isBanned(player);
        this.banReason = SQL.getBanReason(player);
    }

    public void load() {
        this.muted = SQL.isMuted(player);
        this.muteReason = SQL.getMuteReason(player);
        this.banned = SQL.isBanned(player);
        this.banReason = SQL.getBanReason(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void mute(String reason) {
        SQL.setMuted(player, true, reason);
        this.muted = true;
    }

    public String getMuteReason() {
        return muteReason;
    }

    public void unmute() {
        SQL.setMuted(player, false, null);
        this.muted = false;
    }

    public boolean isMuted() {
        return muted;
    }

    public void ban(String reason) {
        SQL.setBanned(player, true, reason);
        this.banned = true;
    }

    public String getBanReason() {
        return banReason;
    }

    public void unban() {
        SQL.setBanned(player, false, null);
        this.banned = false;
    }

    public boolean isBanned() {
        return banned;
    }

}
