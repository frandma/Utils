package me.frandma.utils.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserData {
    private final ConcurrentHashMap<UUID, User> dataMap = new ConcurrentHashMap<>();

    public ConcurrentHashMap<UUID, User> getDataMap() {
        return this.dataMap;
    }

    public void addPlayer(Player player) {
        if (player != null)
            dataMap.put(player.getUniqueId(), new User(player));
    }

    public void addPlayer(String name, UUID uuid) {
        this.dataMap.put(uuid, new User(name, uuid));
    }

    public void removePlayer(Player player) {
        if (player != null)
            dataMap.remove(player.getUniqueId());
    }

    public void removePlayer(UUID uuid) {
        if (Bukkit.getPlayer(uuid) != null) {
            dataMap.remove(uuid);
        }
    }

    public User get(Player player) {
        if (player != null)
            return dataMap.get(player.getUniqueId());
        return null;
    }

    public User get(UUID uuid) {
        return this.dataMap.get(uuid);
    }

}
