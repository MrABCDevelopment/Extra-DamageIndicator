package me.dreamdevs.github.edi.api.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter @Setter
public class ShowActionBarIndicatorEvent extends Event {

    private @Getter static final HandlerList handlerList = new HandlerList();

    private final Player player;
    private final LivingEntity target;
    private final double healthPercentage;
    private String text;

    public ShowActionBarIndicatorEvent(Player player, LivingEntity target, double healthPercentage, String text) {
        this.player = player;
        this.target = target;
        this.healthPercentage = healthPercentage;
        this.text = text;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

}