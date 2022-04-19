package me.MrABCDevelopment.events;

import javafx.scene.layout.Priority;
import me.MrABCDevelopment.HologramsHandler;
import me.MrABCDevelopment.main.EDMMain;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Damage implements Listener {

    EDMMain plugin;

    public Damage(EDMMain plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.isCancelled()) {
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
        if(e.getEntity() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) e.getEntity();
            if(!armorStand.isVisible())
                e.setCancelled(true);
            return;
        }
        plugin.getHologramsHandler().getHologramsAPI().createHologram(e.getEntity().getLocation(), Math.floor(e.getDamage()));


    }
}