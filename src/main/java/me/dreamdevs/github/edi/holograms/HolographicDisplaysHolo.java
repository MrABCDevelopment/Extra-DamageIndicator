package me.dreamdevs.github.edi.holograms;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.Settings;
import me.dreamdevs.github.edi.api.HealthChangeType;
import me.dreamdevs.github.edi.api.IHologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class HolographicDisplaysHolo implements IHologram {

    @Override
    public void createHologram(Location location, HealthChangeType healthChangeType, double health) {
        String healthText = null;
        if(healthChangeType == HealthChangeType.DAMAGE)
            healthText = Settings.damageMessageHolo.replaceAll("%damage%", String.valueOf(health));
        else
            healthText = Settings.regenerationMessageHolo.replaceAll("%health%", String.valueOf(health));

        Hologram holo = HologramsAPI.createHologram(EDIMain.getInstance(), location.add(0,Settings.addYCoordinates,0));
        holo.appendTextLine(healthText);
        holo.getVisibilityManager().setVisibleByDefault(false);
        for(Entity entity : location.getWorld().getNearbyEntities(location, 8, 8, 8)) {
            if(entity instanceof Player)
                holo.getVisibilityManager().showTo((Player) entity);
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously(EDIMain.getInstance(), holo::delete, Settings.cooldown);
    }

}