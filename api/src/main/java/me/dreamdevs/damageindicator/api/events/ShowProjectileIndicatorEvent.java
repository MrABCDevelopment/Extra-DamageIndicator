package me.dreamdevs.damageindicator.api.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter @Setter
public class ShowProjectileIndicatorEvent extends Event {

	private @Getter static final HandlerList handlerList = new HandlerList();

	private final Player player;
	private final LivingEntity target;
	private final double damage;

	public ShowProjectileIndicatorEvent(Player player, LivingEntity target, double damage) {
		this.player = player;
		this.target = target;
		this.damage = damage;
	}

	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}

}