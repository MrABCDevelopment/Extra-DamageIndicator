package me.dreamdevs.github.edi.listeners;

import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.Settings;
import me.dreamdevs.github.edi.api.HealthChangeType;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class EntityRegainHealthListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void RegenEvent(EntityRegainHealthEvent e) {
        if(e.isCancelled()) {
            return;
        }
        if(Settings.isCitizen)
            if(CitizensAPI.getNPCRegistry().isNPC(e.getEntity())) return;
        if((e.getEntity() instanceof Player && !Settings.mobTypes.get("Players"))
          || (e.getEntity() instanceof Animals && !Settings.mobTypes.get("Animals"))
          || (e.getEntity() instanceof Monster && !Settings.mobTypes.get("Mob")))
            return;
        if(Settings.regenerationMessageHolo == null || Settings.regenerationMessageHolo.equalsIgnoreCase("")) return;
        if(e.getEntity() instanceof Player && ((Player) e.getEntity()).isSneaking()) {
            if(!Settings.sneaking)
                return;
            EDIMain.getInstance().getHologramsHandler().getHologram().createHologram(e.getEntity().getLocation(), HealthChangeType.REGENERATION, Math.floor(e.getAmount()));
        } else {
            EDIMain.getInstance().getHologramsHandler().getHologram().createHologram(e.getEntity().getLocation(), HealthChangeType.REGENERATION, Math.floor(e.getAmount()));
        }
    }

}
