package me.frandma.utils.other;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public class PowerTool {
    Map<UUID, String> powerTool = new HashMap<>();
    public Boolean hasPowerTool(Player p) {
        return powerTool.containsKey(p.getUniqueId());
    }
    public void setPowerTool(Player p, Material m, String command) {
        if (m == null || command == null) {
            powerTool.clear();
            return;
        }
        powerTool.put(p.getUniqueId(), m + ":" + command);
    }
    public Material getMaterial(Player p) {
        return Material.getMaterial(powerTool.get(p.getUniqueId()).split(":")[0]);
    }
    public String getCommand(Player p) {
        return powerTool.get(p.getUniqueId()).split(":")[1];
    }
}
