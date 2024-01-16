package me.dreamdevs.damageindicator.api;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

public enum Config {

	// General settings, update checker, random chests
	SETTINGS_HOLOGRAM_STAY("Settings.Hologram-Stay",1),
	SETTINGS_USE_UPDATE_CHECKER("Settings.Use-Update-Checker",true),
	SETTINGS_Y_COORD_ADDITION("Settings.Y-Coord-Addition",1.0),
	SETTINGS_USE_ACTION_BARS("Settings.Use-Action-Bars",true),
	SETTINGS_TYPE_MONSTERS("Settings.Type.Monsters",true),
	SETTINGS_TYPE_ANIMALS("Settings.Type.Animals",true),
	SETTINGS_TYPE_PLAYERS("Settings.Type.Players",true),
	SETTINGS_ACTIVATION_SNEAKING_DAMAGE("Settings.Activation.Sneaking-Damage",true),
	SETTINGS_ACTIVATION_FALL_DAMAGE("Settings.Activation.Fall-Damage",true),
	SETTINGS_ACTIVATION_BURNING_DAMAGE("Settings.Activation.Burning-Damage",true),
	SETTINGS_ACTIVATION_DRAGON_BREATH_DAMAGE("Settings.Activation.Dragon-Breath-Damage",true),
	SETTINGS_ACTIVATION_POISON_DAMAGE("Settings.Activation.Poison-Damage",true),
	SETTINGS_ACTIVATION_STARVATION_DAMAGE("Settings.Activation.Starvation-Damage",false),
	SETTINGS_ACTIVATION_NPC_HEALTH_CHANGE_CITIZENS("Settings.Activation.Npc-Health-Change-Citizens",false),
	SETTINGS_USE_CHAT_PROJECTILE_INDICATOR("Settings.Use-Chat-Projectile-Indicator",true);

	private final String path;
	private final Object def;
	private static YamlConfiguration CONFIG;

	/**
	 * Lang enum constructor
	 */
	Config(String path, Object start) {
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

	public static void reloadFile() {
		try {
			CONFIG.load("config.yml");
		} catch (IOException | InvalidConfigurationException e) {

		}
	}

	@Override
	public String toString() {
		return CONFIG.getString(getPath());
	}

	public boolean toBoolean() {
		return CONFIG.getBoolean(getPath());
	}

	public int toInt() {
		return CONFIG.getInt(getPath());
	}

	public double toDouble() {
		return CONFIG.getDouble(getPath());
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
