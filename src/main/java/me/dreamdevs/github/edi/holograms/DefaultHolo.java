package me.dreamdevs.github.edi.holograms;

import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.Settings;
import me.dreamdevs.github.edi.api.HealthChangeType;
import me.dreamdevs.github.edi.api.IHologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;

public class DefaultHolo implements IHologram {
    @Override
    public void createHologram(Location location, HealthChangeType healthChangeType, double health) {
        Entity entity = location.getWorld().spawnEntity(location.clone().add(0, Settings.addYCoordinates, 0), EntityType.ARMOR_STAND);
        ((ArmorStand) entity).setVisible(false);
        ((ArmorStand) entity).isSmall();
        entity.setCustomNameVisible(true);
        entity.setMetadata("holo", new FixedMetadataValue(EDIMain.getInstance(), "ediholo"));
        entity.setCustomNameVisible(true);
        if(healthChangeType == HealthChangeType.DAMAGE)
            entity.setCustomName(Settings.damageMessageHolo.replace("%damage%", String.valueOf(health)));
        else if(healthChangeType == HealthChangeType.REGENERATION)
            entity.setCustomName(Settings.regenerationMessageHolo.replace("%health%", String.valueOf(health)));
        ((ArmorStand)entity).setGravity(false);
        ((ArmorStand) entity).setMarker(true);
        Bukkit.getScheduler().runTaskLaterAsynchronously(EDIMain.getInstance(), () -> {
            ((LivingEntity)entity).damage(999);
            entity.remove();
        }, Settings.cooldown*20L);
    }

}