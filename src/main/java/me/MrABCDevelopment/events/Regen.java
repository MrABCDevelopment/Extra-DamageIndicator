package me.MrABCDevelopment.events;

import me.MrABCDevelopment.HealthChangeType;
import me.MrABCDevelopment.HologramsHandler;
import me.MrABCDevelopment.main.EDMMain;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class Regen implements Listener {

    EDMMain plugin;

    public Regen(EDMMain main) {
        this.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void RegenEvent(EntityRegainHealthEvent e) {
        if(e.isCancelled()) {
            return;
        }
        if(EDMMain.isCitizens)
            if(CitizensAPI.getNPCRegistry().isNPC(e.getEntity())) return;
        if(e.getEntity() instanceof Player && !HologramsHandler.Types.get("Players") ) {
            return;
        } else if(e.getEntity() instanceof Animals && !HologramsHandler.Types.get("Animals") ) {
            return;
        } else if(e.getEntity() instanceof Monster && !HologramsHandler.Types.get("Mob") ) {
            return;
        }
        if(HologramsHandler.rm == null || HologramsHandler.rm.equalsIgnoreCase("")) return;
        if(e.getEntity() instanceof Player && ((Player) e.getEntity()).isSneaking()) {
            if(!HologramsHandler.sneak)
                return;
            plugin.getHologramsHandler().getHologramsAPI().createHologram(e.getEntity().getLocation(), HealthChangeType.REGENERATION, Math.floor(e.getAmount()));
        } else {
            plugin.getHologramsHandler().getHologramsAPI().createHologram(e.getEntity().getLocation(), HealthChangeType.REGENERATION, Math.floor(e.getAmount()));
        }
    }

}
