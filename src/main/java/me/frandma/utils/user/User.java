package me.frandma.utils.user;

import me.frandma.utils.other.Vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User {
    private final String name;
    private final UUID uuid;
    private boolean vanished;
    private boolean muted;
    private String muteReason;
    private boolean banned;
    private String banReason;

    public User(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.vanished = Vanish.isVanished(player.getUniqueId());
        this.muted = PlayersDB.isMuted(player.getUniqueId());
        this.muteReason = PlayersDB.getMuteReason(player.getUniqueId());
        this.banned = PlayersDB.isBanned(player.getUniqueId());
        this.banReason = PlayersDB.getBanReason(player.getUniqueId());
    }

    public User(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
        this.vanished = Vanish.isVanished(uuid);
        this.muted = PlayersDB.isMuted(uuid);
        this.muteReason = PlayersDB.getMuteReason(uuid);
        this.banned = PlayersDB.isBanned(uuid);
        this.banReason = PlayersDB.getBanReason(uuid);
    }

    public void load() {
        this.vanished = Vanish.isVanished(uuid);
        this.muted = PlayersDB.isMuted(uuid);
        this.muteReason = PlayersDB.getMuteReason(uuid);
        this.banned = PlayersDB.isBanned(uuid);
        this.banReason = PlayersDB.getBanReason(uuid);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void vanish(boolean announceToOtherStaff) {
        Vanish.vanish(Bukkit.getPlayer(uuid), announceToOtherStaff);
        this.vanished = true;
    }

    public void unVanish() {
        Vanish.unvanish(Bukkit.getPlayer(uuid));
        this.vanished = false;
    }

    public boolean isVanished() {
        return Vanish.isVanished(uuid);
    }

    public void mute(String reason) {
        PlayersDB.setMuted(uuid, true, reason);
        this.muted = true;
    }

    public String getMuteReason() {
        return muteReason;
    }

    public void unMute() {
        PlayersDB.setMuted(uuid, false, null);
        this.muted = false;
    }

    public boolean isMuted() {
        return muted;
    }

    public void ban(String reason) {
        PlayersDB.setBanned(uuid, true, reason);
        this.banned = true;
    }

    public String getBanReason() {
        return banReason;
    }

    public void unBan() {
        PlayersDB.setBanned(uuid, false, null);
        this.banned = false;
    }

    public boolean isBanned() {
        return banned;
    }

}
