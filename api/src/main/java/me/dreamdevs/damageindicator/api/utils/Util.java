package me.dreamdevs.damageindicator.api.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.io.File;

@UtilityClass
public class Util {

	public static void sendPluginMessage(String message) {
		Bukkit.getConsoleSender().sendMessage(ColourUtil.colorize(message));
	}

	public static void tryCreateFile(@NotNull File file) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				// To do nothing
			}
		}
	}

}