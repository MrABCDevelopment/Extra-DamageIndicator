package me.MrABCDevelopment;

import me.MrABCDevelopment.main.EDMMain;
import org.bukkit.Location;

public interface HologramsAPI {

    void createHologram(Location location, double Damage);

    void removeHologram();

    EDMMain plugin = EDMMain.getInstance();

}