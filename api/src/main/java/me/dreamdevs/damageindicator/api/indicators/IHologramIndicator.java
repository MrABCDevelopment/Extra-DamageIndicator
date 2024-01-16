package me.dreamdevs.damageindicator.api.indicators;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public interface IHologramIndicator {

	void showHologram(LivingEntity target, HealthChangeType healthChangeType, double health);

	void showActionBar(Player player, LivingEntity target, double health);

}