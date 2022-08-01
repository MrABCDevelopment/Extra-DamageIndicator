package me.mrabcdevelopment.github.edi.holograms;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramManager;
import me.mrabcdevelopment.github.edi.Settings;
import me.mrabcdevelopment.github.edi.api.HealthChangeType;
import me.mrabcdevelopment.github.edi.api.IHologram;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DecentHologramsHolo implements IHologram {

    private HologramManager hologramManager = new HologramManager();

    @Override
    public void createHologram(Player player, Location location, HealthChangeType healthChangeType, double health) {
        String healthText = null;
        if(healthChangeType == HealthChangeType.DAMAGE)
            healthText = Settings.damageMessageHolo.replaceAll("%damage%", String.valueOf(health));
        else
            healthText = Settings.regenerationMessageHolo.replaceAll("%health%", String.valueOf(health));

        hologramManager.spawnTemporaryHologramLine(location, healthText, Settings.cooldown* 20L);
    }
}