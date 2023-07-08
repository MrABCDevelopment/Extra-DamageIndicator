package me.dreamdevs.github.edi.listeners;

import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.api.HealthChangeType;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class EntityHealthChangeListeners implements Listener
{

    @EventHandler(priority = EventPriority.MONITOR)
    public void damageEvent(EntityDamageEvent event) {
        if(event.isCancelled())
            return;

        if(EDIMain.getInstance().getSettingsManager().getValueAsBoolean("citizens-enabled")) {
            if (CitizensAPI.getNPCRegistry().isNPC(event.getEntity()) && !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-npc-health-changes"))
                return;
        }

        // Checks fall damage
        if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("hostile") && event.getEntity() instanceof Monster)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("players") && event.getEntity() instanceof Player)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("animals") && event.getEntity() instanceof Animals)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-when-fell-from-height"))
                return;

            EDIMain.getInstance().getIndicatorManager().createHologramIndicator(event.getEntity().getLocation(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
        }

        // Checks burning damage
        if(event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("hostile") && event.getEntity() instanceof Monster)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("players") && event.getEntity() instanceof Player)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("animals") && event.getEntity() instanceof Animals)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-burning"))
                return;

            EDIMain.getInstance().getIndicatorManager().createHologramIndicator(event.getEntity().getLocation(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
        }

        if(event.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH) {
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("hostile") && event.getEntity() instanceof Monster)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("players") && event.getEntity() instanceof Player)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("animals") && event.getEntity() instanceof Animals)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-in-dragon-breath"))
                return;

            EDIMain.getInstance().getIndicatorManager().createHologramIndicator(event.getEntity().getLocation(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
        }

        if(event.getCause() == EntityDamageEvent.DamageCause.POISON) {
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("hostile") && event.getEntity() instanceof Monster)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("players") && event.getEntity() instanceof Player)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("animals") && event.getEntity() instanceof Animals)
                return;
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-has-poison"))
                return;

            EDIMain.getInstance().getIndicatorManager().createHologramIndicator(event.getEntity().getLocation(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
        }

    }

    @EventHandler
    public void damageByEntityEvent(EntityDamageByEntityEvent event) {
        if(event.isCancelled())
            return;

        if(EDIMain.getInstance().getSettingsManager().getValueAsBoolean("citizens-enabled")) {
            if (CitizensAPI.getNPCRegistry().isNPC(event.getEntity()) && !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-npc-health-changes"))
                return;
        }

        if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("hostile") && event.getEntity() instanceof Monster)
            return;
        if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("players") && event.getEntity() instanceof Player)
            return;
        if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("animals") && event.getEntity() instanceof Animals)
            return;
        if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-burning"))
            return;

        if(event.getEntity() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) event.getEntity();
            if(!armorStand.isVisible() && armorStand.hasMetadata("holo"))
                event.setCancelled(true);
            return;
        }

        if(event.getEntity() instanceof Player && ((Player) event.getEntity()).isSneaking()) {
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-sneaking"))
                return;
        }

        if(EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-action-bar")) {
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                EDIMain.getInstance().getIndicatorManager().showActionBar(player, (LivingEntity) event.getEntity(), event.getDamage());
            }
        }

        EDIMain.getInstance().getIndicatorManager().createHologramIndicator(event.getEntity().getLocation(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
    }

    @EventHandler
    public void regainHealthEvent(EntityRegainHealthEvent event) {
        if(event.isCancelled())
            return;

        if(EDIMain.getInstance().getSettingsManager().getValueAsBoolean("citizens-enabled")) {
            if (CitizensAPI.getNPCRegistry().isNPC(event.getEntity()) && !EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-npc-health-changes"))
                return;
        }

        if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("hostile") && event.getEntity() instanceof Monster)
            return;
        if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("players") && event.getEntity() instanceof Player)
            return;
        if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("animals") && event.getEntity() instanceof Animals)
            return;

        if(event.getEntity() instanceof Player && ((Player) event.getEntity()).isSneaking()) {
            if(!EDIMain.getInstance().getSettingsManager().getValueAsBoolean("show-while-sneaking"))
                return;
        }

        EDIMain.getInstance().getIndicatorManager().createHologramIndicator(event.getEntity().getLocation(), HealthChangeType.REGENERATION, Math.floor(event.getAmount()));
    }

}