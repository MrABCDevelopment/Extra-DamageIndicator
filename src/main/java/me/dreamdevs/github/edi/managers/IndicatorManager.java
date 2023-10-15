package me.dreamdevs.github.edi.managers;

import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.api.HealthChangeType;
import me.dreamdevs.github.edi.api.events.ShowActionBarIndicatorEvent;
import me.dreamdevs.github.edi.utils.ColourUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;

public class IndicatorManager
{

    public void createHologramIndicator(Location location, HealthChangeType healthChangeType, double health) {
        ArmorStand entity = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0, EDIMain.getInstance().getSettingsManager().getValueAsDouble("y-coord-addition"), 0), EntityType.ARMOR_STAND);
        entity.setVisible(false);
        entity.isSmall();
        entity.setCustomNameVisible(true);
        entity.setMetadata("holo", new FixedMetadataValue(EDIMain.getInstance(), "ediholo"));
        entity.setCustomNameVisible(true);
        if(healthChangeType == HealthChangeType.DAMAGE)
            entity.setCustomName(EDIMain.getInstance().getSettingsManager().getMessage("hologram-damage-message").replace("%AMOUNT%", String.valueOf(health)));
        else if(healthChangeType == HealthChangeType.REGENERATION)
            entity.setCustomName(EDIMain.getInstance().getSettingsManager().getMessage("hologram-regeneration-message").replace("%AMOUNT%", String.valueOf(health)));
        entity.setGravity(false);
        entity.setMarker(true);
        Bukkit.getScheduler().runTaskLaterAsynchronously(EDIMain.getInstance(), () -> {
            entity.damage(999);
            entity.remove();
        }, EDIMain.getInstance().getSettingsManager().getValueAsInt("hologram-stay")*20L);
    }

    public void showActionBar(Player player, LivingEntity target, double damage) {
        StringBuilder stringBuilder = new StringBuilder();

        double percentage = Math.floor((target.getHealth()-damage) / target.getMaxHealth()*100);
        for(int x = 0; x<10; x++) {
            if(x < (percentage/10)) {
                stringBuilder.append("&a■");
            } else {
                stringBuilder.append("&c■");
            }
        }
        if(percentage < 0)
            percentage = 0;
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColourUtil.colorize(stringBuilder.toString()+" "+percentage+"%")));

        ShowActionBarIndicatorEvent showActionBarIndicatorEvent = new ShowActionBarIndicatorEvent(player, target, percentage, stringBuilder.toString());
        Bukkit.getPluginManager().callEvent(showActionBarIndicatorEvent);
    }

}