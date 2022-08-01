package me.mrabcdevelopment.github.edi.holograms;

import me.mrabcdevelopment.github.edi.EDIMain;
import me.mrabcdevelopment.github.edi.Settings;
import me.mrabcdevelopment.github.edi.api.HealthChangeType;
import me.mrabcdevelopment.github.edi.api.IHologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;

public class DefaultHolo implements IHologram {
    @Override
    public void createHologram(Player player, Location location, HealthChangeType healthChangeType, double health) {
        Entity entity = location.getWorld().spawnEntity(location.clone().add(0, Settings.addYCoordinates, 0), EntityType.ARMOR_STAND);
        ((ArmorStand) entity).setVisible(false);
        ((ArmorStand) entity).isSmall();
        entity.setCustomNameVisible(true);
        if(healthChangeType == HealthChangeType.DAMAGE)
            entity.setCustomName(Settings.damageMessageHolo.replace("%damage%", String.valueOf(health)));
        else if(healthChangeType == HealthChangeType.REGENERATION)
            entity.setCustomName(Settings.regenerationMessageHolo.replace("%health%", String.valueOf(health)));
        ((ArmorStand)entity).setGravity(false);
        ((ArmorStand) entity).setMarker(false);
        Bukkit.getScheduler().runTaskLaterAsynchronously(EDIMain.getInstance(), new Runnable() {
            @Override
            public void run() {
                ((LivingEntity)entity).damage(999);
                ((LivingEntity)entity).remove();
            }
        }, Settings.cooldown*20L);
    }

}