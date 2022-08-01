package me.mrabcdevelopment.github.edi.holograms;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.mrabcdevelopment.github.edi.EDIMain;
import me.mrabcdevelopment.github.edi.Settings;
import me.mrabcdevelopment.github.edi.api.HealthChangeType;
import me.mrabcdevelopment.github.edi.api.IHologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HolographicDisplaysHolo implements IHologram {

    @Override
    public void createHologram(Player player, Location location, HealthChangeType healthChangeType, double health) {
        String healthText = null;
        if(healthChangeType == HealthChangeType.DAMAGE)
            healthText = Settings.damageMessageHolo.replaceAll("%damage%", String.valueOf(health));
        else
            healthText = Settings.regenerationMessageHolo.replaceAll("%health%", String.valueOf(health));

        Hologram holo = HologramsAPI.createHologram(EDIMain.getInstance(), location.add(0,Settings.addYCoordinates,0));
        holo.appendTextLine(healthText);
        holo.getVisibilityManager().setVisibleByDefault(false);
        holo.getVisibilityManager().showTo(player);
        Bukkit.getScheduler().runTaskLaterAsynchronously(EDIMain.getInstance(), new Runnable() {
            @Override
            public void run() {
                holo.delete();
            }
        }, Settings.cooldown);
    }

}