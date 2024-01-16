package me.dreamdevs.damageindicator.listeners;

import me.dreamdevs.damageindicator.ExtraDamageIndicatorMain;
import me.dreamdevs.damageindicator.api.Config;
import me.dreamdevs.damageindicator.api.Language;
import me.dreamdevs.damageindicator.api.events.ShowActionBarIndicatorEvent;
import me.dreamdevs.damageindicator.api.events.ShowProjectileIndicatorEvent;
import me.dreamdevs.damageindicator.api.indicators.HealthChangeType;
import me.dreamdevs.damageindicator.api.utils.VersionUtil;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public final class EntityHealthChangeListeners implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void damageEvent(EntityDamageEvent event) {
        if (event.isCancelled())
            return;

        if(Bukkit.getPluginManager().getPlugin("Citizens") != null) {
            if (CitizensAPI.getNPCRegistry().isNPC(event.getEntity()) && !Config.SETTINGS_ACTIVATION_NPC_HEALTH_CHANGE_CITIZENS.toBoolean())
                return;
        }

        // Checks fall damage
        if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if(!Config.SETTINGS_TYPE_MONSTERS.toBoolean() && event.getEntity() instanceof Monster)
                return;
            if(!Config.SETTINGS_TYPE_PLAYERS.toBoolean() && event.getEntity() instanceof Player)
                return;
            if(!Config.SETTINGS_TYPE_ANIMALS.toBoolean() && event.getEntity() instanceof Animals)
                return;
            if(!Config.SETTINGS_ACTIVATION_FALL_DAMAGE.toBoolean())
                return;

            ExtraDamageIndicatorMain.getInstance().getIndicatorManager().getHologramIndicator().showHologram((LivingEntity) event.getEntity(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
        }

        // Checks burning damage
        if(event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
            if(!Config.SETTINGS_TYPE_MONSTERS.toBoolean() && event.getEntity() instanceof Monster)
                return;
            if(!Config.SETTINGS_TYPE_PLAYERS.toBoolean() && event.getEntity() instanceof Player)
                return;
            if(!Config.SETTINGS_TYPE_ANIMALS.toBoolean() && event.getEntity() instanceof Animals)
                return;
            if(!Config.SETTINGS_ACTIVATION_BURNING_DAMAGE.toBoolean())
                return;

            ExtraDamageIndicatorMain.getInstance().getIndicatorManager().getHologramIndicator().showHologram((LivingEntity) event.getEntity(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
        }

        if (!VersionUtil.getVersion().contains("1_8")) {

            if (event.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH) {
                if (!Config.SETTINGS_TYPE_MONSTERS.toBoolean() && event.getEntity() instanceof Monster)
                    return;
                if (!Config.SETTINGS_TYPE_PLAYERS.toBoolean() && event.getEntity() instanceof Player)
                    return;
                if (!Config.SETTINGS_TYPE_ANIMALS.toBoolean() && event.getEntity() instanceof Animals)
                    return;
                if (!Config.SETTINGS_ACTIVATION_DRAGON_BREATH_DAMAGE.toBoolean())
                    return;

                ExtraDamageIndicatorMain.getInstance().getIndicatorManager().getHologramIndicator().showHologram((LivingEntity) event.getEntity(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
            }

        }

        if(event.getCause() == EntityDamageEvent.DamageCause.POISON) {
            if(!Config.SETTINGS_TYPE_MONSTERS.toBoolean() && event.getEntity() instanceof Monster)
                return;
            if(!Config.SETTINGS_TYPE_PLAYERS.toBoolean() && event.getEntity() instanceof Player)
                return;
            if(!Config.SETTINGS_TYPE_ANIMALS.toBoolean() && event.getEntity() instanceof Animals)
                return;
            if(!Config.SETTINGS_ACTIVATION_POISON_DAMAGE.toBoolean())
                return;

            ExtraDamageIndicatorMain.getInstance().getIndicatorManager().getHologramIndicator().showHologram((LivingEntity) event.getEntity(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
        }
    }

    @EventHandler
    public void damageByEntityEvent(EntityDamageByEntityEvent event) {
        if(event.isCancelled())
            return;

        if(Bukkit.getPluginManager().getPlugin("Citizens") != null) {
            if (CitizensAPI.getNPCRegistry().isNPC(event.getEntity()) && !Config.SETTINGS_ACTIVATION_NPC_HEALTH_CHANGE_CITIZENS.toBoolean())
                return;
        }

        if(!Config.SETTINGS_TYPE_MONSTERS.toBoolean() && event.getEntity() instanceof Monster)
            return;
        if(!Config.SETTINGS_TYPE_PLAYERS.toBoolean() && event.getEntity() instanceof Player)
            return;
        if(!Config.SETTINGS_TYPE_ANIMALS.toBoolean() && event.getEntity() instanceof Animals)
            return;
        if(!Config.SETTINGS_ACTIVATION_BURNING_DAMAGE.toBoolean())
            return;

        if (event.getEntity() instanceof Player && ((Player) event.getEntity()).isSneaking()) {
            if(!Config.SETTINGS_ACTIVATION_SNEAKING_DAMAGE.toBoolean())
                return;
        }

        if(Config.SETTINGS_USE_ACTION_BARS.toBoolean()) {
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                ExtraDamageIndicatorMain.getInstance().getIndicatorManager().getHologramIndicator().showActionBar(player, (LivingEntity) event.getEntity(), event.getDamage());
            }
        }

        if (Config.SETTINGS_USE_CHAT_PROJECTILE_INDICATOR.toBoolean()) {
            if (!(event.getDamager() instanceof Player)) {
                return;
            }

            if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
                Projectile projectile = (Projectile) event.getDamager();
                Player player = (Player) projectile.getShooter();

                player.sendMessage(Language.INDICATOR_CHAT_PROJECTILE_DAMAGE_MESSAGE.toString().replace("%AMOUNT%", String.valueOf(event.getDamage())));

                ShowProjectileIndicatorEvent showProjectileIndicatorEvent = new ShowProjectileIndicatorEvent(player, (LivingEntity) event.getEntity(), event.getDamage());
                Bukkit.getPluginManager().callEvent(showProjectileIndicatorEvent);
            }
        }

        ExtraDamageIndicatorMain.getInstance().getIndicatorManager().getHologramIndicator().showHologram((LivingEntity) event.getEntity(), HealthChangeType.DAMAGE, Math.floor(event.getDamage()));
    }

    @EventHandler
    public void regainHealthEvent(EntityRegainHealthEvent event) {
        if(event.isCancelled())
            return;

        if(Bukkit.getPluginManager().getPlugin("Citizens") != null) {
            if (CitizensAPI.getNPCRegistry().isNPC(event.getEntity()) && !Config.SETTINGS_ACTIVATION_NPC_HEALTH_CHANGE_CITIZENS.toBoolean())
                return;
        }

        if(!Config.SETTINGS_TYPE_MONSTERS.toBoolean() && event.getEntity() instanceof Monster)
            return;
        if(!Config.SETTINGS_TYPE_PLAYERS.toBoolean() && event.getEntity() instanceof Player)
            return;
        if(!Config.SETTINGS_TYPE_ANIMALS.toBoolean() && event.getEntity() instanceof Animals)
            return;

        if(event.getEntity() instanceof Player && ((Player) event.getEntity()).isSneaking()) {
            if(!Config.SETTINGS_ACTIVATION_SNEAKING_DAMAGE.toBoolean())
                return;
        }

        ExtraDamageIndicatorMain.getInstance().getIndicatorManager().getHologramIndicator().showHologram((LivingEntity) event.getEntity(), HealthChangeType.REGENERATION, Math.floor(event.getAmount()));
    }

}