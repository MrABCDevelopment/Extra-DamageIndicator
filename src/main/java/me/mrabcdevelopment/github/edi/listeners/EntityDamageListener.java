package me.mrabcdevelopment.github.edi.listeners;

import me.mrabcdevelopment.github.edi.EDIMain;
import me.mrabcdevelopment.github.edi.HologramsHandler;
import me.mrabcdevelopment.github.edi.Settings;
import me.mrabcdevelopment.github.edi.api.HealthChangeType;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Animals;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.isCancelled()) return;

        if(Settings.isCitizen) {
            if (CitizensAPI.getNPCRegistry().isNPC(event.getEntity())) return;
        }
        if(event.getEntity() instanceof Player && !Settings.mobTypes.get("Players") ) {
            return;
        } else if(event.getEntity() instanceof Animals && !Settings.mobTypes.get("Animals") ) {
            return;
        } else if(event.getEntity() instanceof Monster && !Settings.mobTypes.get("Mob") ) {
            return;
        }
        if(event.getEntity() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) event.getEntity();
            if(!armorStand.isVisible())
                event.setCancelled(true);
            return;
        }
        if(Settings.damageMessageHolo == null || Settings.damageMessageHolo.equalsIgnoreCase("")) return;
        if(event.getEntity() instanceof Player && ((Player) event.getEntity()).isSneaking()) {
            if(!Settings.sneaking)
                return;
        }
        EDIMain.getInstance().getHologramsHandler().getHologram().createHologram(event.getEntity().getLocation(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
    }

}