package me.mrabcdevelopment.github.edi.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IHologram {
    void createHologram(Player player, Location location, HealthChangeType healthChangeType, double health);

}