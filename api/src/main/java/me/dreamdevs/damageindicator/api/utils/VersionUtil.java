package me.dreamdevs.damageindicator.api.utils;

import lombok.Getter;
import org.bukkit.Bukkit;

public class VersionUtil {

	private @Getter static VersionUtil instance;

	public VersionUtil() {
		instance = this;
		setupVersion();
	}

	public void setupVersion() {
		Util.sendPluginMessage("&aYou are using "+getVersion()+" version.");
		switch (getVersion()) {
			case "v1_8_R3":
			case "v1_12_R1":
			case "v1_13_R2":
			case "v1_17_R1":
			case "v1_19_R3":
			case "v1_20_R1":
			case "v1_20_R3":
				Util.sendPluginMessage("&aEverything works fine!!");
				break;

			default:
				Util.sendPluginMessage("&cUnknown and unsupported minecraft version!");
				break;
		}
	}

	public static String getVersion() {
		String packageName = Bukkit.getServer().getClass().getPackage().getName();
		return packageName.substring(packageName.lastIndexOf(".") + 1);
	}

}