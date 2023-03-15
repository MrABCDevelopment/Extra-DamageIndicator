package me.dreamdevs.github.edi.holograms;

import eu.decentsoftware.holograms.api.holograms.HologramManager;
import me.dreamdevs.github.edi.Settings;
import me.dreamdevs.github.edi.api.HealthChangeType;
import me.dreamdevs.github.edi.api.IHologram;
import org.bukkit.Location;

public class DecentHologramsHolo implements IHologram {

    private HologramManager hologramManager = new HologramManager();

    @Override
    public void createHologram(Location location, HealthChangeType healthChangeType, double health) {
        String healthText = null;
        if(healthChangeType == HealthChangeType.DAMAGE)
            healthText = Settings.damageMessageHolo.replaceAll("%damage%", String.valueOf(health));
        else
            healthText = Settings.regenerationMessageHolo.replaceAll("%health%", String.valueOf(health));

        hologramManager.spawnTemporaryHologramLine(location, healthText, Settings.cooldown* 20L);
    }
}