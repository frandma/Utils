package me.frandma.utils.user;

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

    public void removePlayer(Player player) {
        if (player != null)
            dataMap.remove(player.getUniqueId());
    }

    public User get(Player player) {
        if (player != null)
            return dataMap.get(player.getUniqueId());
        return null;
    }

}
