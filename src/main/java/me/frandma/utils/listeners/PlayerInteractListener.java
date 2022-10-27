package me.frandma.utils.listeners;

import me.frandma.utils.other.PowerTool;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!PowerTool.hasPowerTool(p)) return;
        if (PowerTool.getMaterial(p) != p.getItemInHand().getType()) return;
        p.chat(PowerTool.getCommand(p));
    }
}
