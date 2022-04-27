package me.MrABCDevelopment.holograms;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import me.MrABCDevelopment.HealthChangeType;
import me.MrABCDevelopment.HologramsAPI;
import me.MrABCDevelopment.HologramsHandler;
import me.MrABCDevelopment.main.EDMMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class HolographicDisplaysEDI implements HologramsAPI {

    @Override
    public void createHologram(Location location, HealthChangeType healthChangeType, double EdiHolo) {
        Hologram holo2 = com.gmail.filoghost.holographicdisplays.api.HologramsAPI.createHologram(plugin, location.add(0, HologramsHandler.y, 0));

        if(healthChangeType == HealthChangeType.DAMAGE)
            holo2.appendTextLine( "" + HologramsHandler.dm.replace("%EDAMAGE%", String.valueOf(EdiHolo)));
         else if(healthChangeType == HealthChangeType.REGENERATION)
            holo2.appendTextLine( "" + HologramsHandler.rm.replace("%EHEALTH%", String.valueOf(EdiHolo)));

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                holo2.delete();
            }
        }, 20L*HologramsHandler.c);
    }

    @Override
    public void removeHologram() {
        for (Hologram holograms : com.gmail.filoghost.holographicdisplays.api.HologramsAPI.getHolograms(EDMMain.getInstance())) {
            holograms.delete();
        }
    }
}