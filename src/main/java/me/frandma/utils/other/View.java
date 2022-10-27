package me.frandma.utils.other;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@UtilityClass
public class View {
    private Map<UUID, List<UUID>> viewers = new HashMap<>();

    public void addViewer(Player viewer, Player target) {
        List<UUID> viewerss = viewers.get(viewer);
        if (viewerss == null) viewerss = new ArrayList<>();
        viewerss.add(viewer.getUniqueId());
        viewers.put(target.getUniqueId(), viewerss);
    }

    public void removePlayerFromViewerList(Player viewer) {
        for (UUID target : viewers.keySet()) {
            if (getViewers(Bukkit.getPlayer(target)).contains(viewer)){
                viewers.get(target).remove(viewer);
            }
        }
    }

    public List<UUID> getViewers(Player target) {
        return viewers.get(target.getUniqueId());
    }

    public Inventory formatInventory(PlayerInventory inv) {
        Inventory inventory = Bukkit.createInventory(null, 5*9, inv.getHolder().getName() + "'s Inventory");

        ItemStack pane = new ItemStack(Material.BEDROCK);
        ItemMeta meta = pane.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("§f< Item in hand");
        lore.add("§fItem on cursor >");
        meta.setLore(lore);
        pane.setItemMeta(meta);
        inventory.setItem(7, pane);

        int i = 3;
        for (ItemStack item : inv.getArmorContents()) {
            inventory.setItem(i, item);
            i--;
        }

        if (inv.getItemInHand() != null) {
            inventory.setItem(6, inv.getItemInHand());
        }
        if (inv.getHolder().getItemOnCursor() != null) {
            inventory.setItem(8, inv.getHolder().getItemOnCursor());
        }

        i = 9;
        for (ItemStack item : inv.getContents()) {
            inventory.setItem(i, item);
            i++;
        }

        return inventory;
    }
}
