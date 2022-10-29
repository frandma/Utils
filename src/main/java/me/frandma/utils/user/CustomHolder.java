package me.frandma.utils.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

public class CustomHolder implements InventoryHolder {

    private final UUID viewer, target;

    public Player getViewer() {
        return Bukkit.getPlayer(viewer);
    }

    public Player getTarget() {
        return Bukkit.getPlayer(target);
    }

    public CustomHolder(Player viewer, Player target) {
        this.viewer = viewer.getUniqueId();
        this.target = target.getUniqueId();
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
