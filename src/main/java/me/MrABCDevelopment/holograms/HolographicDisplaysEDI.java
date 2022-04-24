package me.MrABCDevelopment.holograms;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import me.MrABCDevelopment.HologramsAPI;
import me.MrABCDevelopment.HologramsHandler;
import me.MrABCDevelopment.main.EDMMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class HolographicDisplaysEDI implements HologramsAPI {

    @Override
    public void createHologram(Location location, double Damage) {
        Hologram holo2 = com.gmail.filoghost.holographicdisplays.api.HologramsAPI.createHologram(plugin, location.add(0, HologramsHandler.y, 0));
        holo2.appendTextLine( "" + HologramsHandler.m.replace("%EDAMAGE%", String.valueOf(Damage)));
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