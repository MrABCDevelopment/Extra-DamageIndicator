package me.dreamdevs.github.edi.api;

import org.bukkit.Location;
public interface IHologram {
    void createHologram(Location location, HealthChangeType healthChangeType, double health);

}