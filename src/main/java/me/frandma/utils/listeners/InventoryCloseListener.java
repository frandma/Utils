package me.frandma.utils.listeners;

import me.frandma.utils.other.View;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        View.removePlayerFromViewerList(((Player) e.getPlayer()));
    }
}
