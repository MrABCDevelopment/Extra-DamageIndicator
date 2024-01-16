package me.dreamdevs.damageindicator.api;

import me.dreamdevs.damageindicator.api.utils.ColourUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Objects;

public enum Language {

	// Chest messages
	INDICATOR_HOLOGRAM_DAMAGE_MESSAGE("Indicator.Hologram.Damage-Message", "&c-&4%AMOUNT%"),
	INDICATOR_HOLOGRAM_REGENERATION_MESSAGE("Indicator.Hologram.Regeneration-Message","&a+&2%AMOUNT%"),
	INDICATOR_CHAT_PROJECTILE_DAMAGE_MESSAGE("Indicator.Chat.Projectile.Damage-Message","&aYou dealt &c%AMOUNT% damage!"),
	CONSOLE_CANNOT_PERFORM("Console-Cannot-Perform","&cOnly players can perform this command!");

	private final String path;
	private final Object def;
	private static YamlConfiguration CONFIG;

	/**
	 * Lang enum constructor
	 */
	Language(String path, Object start) {
		this.path = path;
		this.def = start;
	}

	/**
	 * Set the {@code YamlConfiguration} to use.
	 *
	 * @param config
	 * The config to set.
	 */
	public static void setFile(YamlConfiguration config) {
		CONFIG = config;
	}

	public static void reloadLanguage() {
		try {
			CONFIG.load("language.yml");
		} catch (Exception e) {

		}
	}

	@Override
	public String toString() {
		return ColourUtil.colorize(Objects.requireNonNull(CONFIG.getString(getPath())));
	}

	/**
	 * Get the default value of the path.
	 *
	 * @return The default value of the path.
	 */
	public Object getDefault() {
		return this.def;
	}

	/**
	 * Get the path to the string.
	 *
	 * @return The path to the string.
	 */
	public String getPath() {
		return this.path;
	}
}
