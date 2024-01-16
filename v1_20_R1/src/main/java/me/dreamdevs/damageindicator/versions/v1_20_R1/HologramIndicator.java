package me.dreamdevs.damageindicator.versions.v1_20_R1;

import me.dreamdevs.damageindicator.api.Config;
import me.dreamdevs.damageindicator.api.DamageIndicatorApi;
import me.dreamdevs.damageindicator.api.Language;
import me.dreamdevs.damageindicator.api.events.ShowActionBarIndicatorEvent;
import me.dreamdevs.damageindicator.api.indicators.HealthChangeType;
import me.dreamdevs.damageindicator.api.indicators.IHologramIndicator;
import me.dreamdevs.damageindicator.api.utils.ColourUtil;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class HologramIndicator implements IHologramIndicator {

	@Override
	public void showHologram(LivingEntity target, HealthChangeType healthChangeType, double health) {
		CraftWorld craftWorld = (CraftWorld) target.getLocation().getWorld();

		Location location = target.getEyeLocation();

		EntityArmorStand entityArmorStand = new EntityArmorStand(EntityTypes.d, craftWorld.getHandle());
		CraftArmorStand craftArmorStand = new CraftArmorStand((CraftServer) Bukkit.getServer(), entityArmorStand);

		craftArmorStand.setGravity(false);
		craftArmorStand.setVisible(false);
		craftArmorStand.setCustomNameVisible(true);
		craftArmorStand.setMarker(true);

		if (healthChangeType == HealthChangeType.DAMAGE) {
			craftArmorStand.setCustomName(Language.INDICATOR_HOLOGRAM_DAMAGE_MESSAGE.toString().replace("%AMOUNT%", String.valueOf(health)));
		}
		if (healthChangeType == HealthChangeType.REGENERATION) {
			craftArmorStand.setCustomName(Language.INDICATOR_HOLOGRAM_REGENERATION_MESSAGE.toString().replace("%AMOUNT%", String.valueOf(health)));
		}

		entityArmorStand = craftArmorStand.getHandle();
		entityArmorStand.a(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());

		PacketPlayOutSpawnEntity packetPlayOutSpawnEntity = new PacketPlayOutSpawnEntity(entityArmorStand, entityArmorStand.af());
		Bukkit.getOnlinePlayers().forEach(player -> ((CraftPlayer)player).getHandle().c.a(packetPlayOutSpawnEntity, null));

		EntityArmorStand finalEntityArmorStand = entityArmorStand;
		Bukkit.getScheduler().runTaskLaterAsynchronously(DamageIndicatorApi.plugin, () -> {
			PacketPlayOutEntityDestroy packetPlayOutEntityDestroy = new PacketPlayOutEntityDestroy(finalEntityArmorStand.af());
			Bukkit.getOnlinePlayers().forEach(player -> ((CraftPlayer)player).getHandle().c.a(packetPlayOutEntityDestroy, null));
		}, Config.SETTINGS_HOLOGRAM_STAY.toInt()*20L);
	}

	@Override
	public void showActionBar(Player player, LivingEntity target, double health) {
		StringBuilder stringBuilder = new StringBuilder();

		double percentage = Math.floor((target.getHealth()-health) / target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*100);
		for(int x = 0; x<10; x++) {
			if(x < (percentage/10)) {
				stringBuilder.append("&a■");
			} else {
				stringBuilder.append("&c■");
			}
		}
		if(percentage < 0)
			percentage = 0;

		ClientboundSystemChatPacket clientboundSystemChatPacket = new ClientboundSystemChatPacket(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + ColourUtil.colorize(stringBuilder.toString()) + "\"}"), true);
		((CraftPlayer)player).getHandle().c.a(clientboundSystemChatPacket, null);

		ShowActionBarIndicatorEvent showActionBarIndicatorEvent = new ShowActionBarIndicatorEvent(player, target, percentage, stringBuilder.toString());
		Bukkit.getPluginManager().callEvent(showActionBarIndicatorEvent);
	}

}