package me.dreamdevs.damageindicator.versions.v1_20_R3;

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
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class HologramIndicator implements IHologramIndicator {

	@Override
	public void showHologram(LivingEntity target, HealthChangeType healthChangeType, double health) {
		CraftWorld craftWorld = (CraftWorld) target.getLocation().getWorld();

		Location location = target.getEyeLocation();

		EntityArmorStand entityArmorStand = new EntityArmorStand(craftWorld.getHandle(), location.getX(), location.getY(), location.getZ());

		entityArmorStand.getBukkitEntity().setGravity(false);
		entityArmorStand.getBukkitEntity().setInvulnerable(true);
		((CraftArmorStand)entityArmorStand.getBukkitEntity()).setMarker(true);
		((CraftArmorStand)entityArmorStand.getBukkitEntity()).setInvisible(true);
		entityArmorStand.getBukkitEntity().setCustomNameVisible(true);

		if (healthChangeType == HealthChangeType.DAMAGE) {
			entityArmorStand.getBukkitEntity().setCustomName(Language.INDICATOR_HOLOGRAM_DAMAGE_MESSAGE.toString().replace("%AMOUNT%", String.valueOf(health)));
		}
		if (healthChangeType == HealthChangeType.REGENERATION) {
			entityArmorStand.getBukkitEntity().setCustomName(Language.INDICATOR_HOLOGRAM_REGENERATION_MESSAGE.toString().replace("%AMOUNT%", String.valueOf(health)));
		}

		PacketPlayOutSpawnEntity packetPlayOutSpawnEntity = new PacketPlayOutSpawnEntity(entityArmorStand, entityArmorStand.aj());
		PacketPlayOutEntityMetadata packetPlayOutEntityMetadata = new PacketPlayOutEntityMetadata(entityArmorStand.aj(), entityArmorStand.an().c());
		Bukkit.getOnlinePlayers().forEach(player -> {
			((CraftPlayer)player).getHandle().c.a(packetPlayOutSpawnEntity, null);
			((CraftPlayer)player).getHandle().c.a(packetPlayOutEntityMetadata, null);
		});

		Bukkit.getScheduler().runTaskLaterAsynchronously(DamageIndicatorApi.plugin, () -> {
			PacketPlayOutEntityDestroy packetPlayOutEntityDestroy = new PacketPlayOutEntityDestroy(entityArmorStand.aj());
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