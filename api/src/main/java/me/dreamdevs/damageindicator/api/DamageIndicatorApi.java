package me.dreamdevs.damageindicator.api;

import me.dreamdevs.damageindicator.api.utils.VersionUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class DamageIndicatorApi {

	public static JavaPlugin plugin;

	public static void loadApi(JavaPlugin instance) {
		plugin = instance;

		new VersionUtil();
	}

}