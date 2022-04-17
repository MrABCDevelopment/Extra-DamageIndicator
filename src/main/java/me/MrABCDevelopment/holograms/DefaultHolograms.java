package me.MrABCDevelopment.holograms;

import me.MrABCDevelopment.HologramsAPI;
import me.MrABCDevelopment.HologramsHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitScheduler;

public class DefaultHolograms implements HologramsAPI {

    BukkitScheduler scheduler = Bukkit.getScheduler();

    @Override
    public void createHologram(Location location, double Damage) {
        Entity entity = location.getWorld().spawnEntity(location.clone().add(0, HologramsHandler.y, 0), EntityType.ARMOR_STAND);
        ((ArmorStand) entity).setVisible(false);
        ((ArmorStand) entity).isSmall();
        ((ArmorStand) entity).setCustomNameVisible(true);;
        entity.setCustomName(HologramsHandler.m.replace("%EDAMAGE%", String.valueOf(Damage)));
        ((ArmorStand)entity).setGravity(false);
        HologramsHandler.ArmorStands.put((ArmorStand) entity, System.currentTimeMillis());
    }

    @Override
    public void removeHologram() {
        scheduler.runTaskTimerAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                if(HologramsHandler.ArmorStands.isEmpty()) return;
                HologramsHandler.ArmorStands.forEach((entity, l) -> {
                    if(l < System.currentTimeMillis()) {
                        ((LivingEntity) entity).remove();
                        ((LivingEntity) entity).damage(999);
                    }
                });
                HologramsHandler.ArmorStands.clear();
            }
        }, 0L, 20L * HologramsHandler.c);
    }
}
