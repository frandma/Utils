package me.frandma.utils.listeners;

import me.frandma.utils.other.View;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.UUID;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().contains("'s Inventory")) {
            int s = e.getSlot();
            if (!(e.getWhoClicked() instanceof Player) || s == 7) {
                e.setCancelled(true);
                return;
            }
            Player viewer = (Player) e.getWhoClicked();
            Player target = Bukkit.getPlayer(e.getInventory().getTitle().split("'")[0]);
            PlayerInventory targetInv = target.getInventory();
            if (s == 0) {
                targetInv.setHelmet(viewer.getItemOnCursor());
            }
            if (s == 1) {
                targetInv.setChestplate(viewer.getItemOnCursor());
            }
            if (s == 2) {
                targetInv.setLeggings(viewer.getItemOnCursor());
            }
            if (s == 3) {
                targetInv.setBoots(viewer.getItemOnCursor());
            }
            if (s == 6) {
                targetInv.setItemInHand(viewer.getItemOnCursor());
            }
            if (s == 8) {
                target.setItemOnCursor(viewer.getItemOnCursor());
            }
            if (s > 8) {
                targetInv.setItem(s - 8, viewer.getItemOnCursor());
            }
        }
        Player p = (Player) e.getWhoClicked();
        if (View.getViewers(p) == null) return;
        for (UUID pUUID : View.getViewers(p)) {
            Bukkit.getPlayer(pUUID).openInventory(View.formatInventory(p.getInventory()));
        }
    }
}
