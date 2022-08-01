package me.mrabcdevelopment.github.edi;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class Settings {

    public static String damageMessageHolo;
    public static String regenerationMessageHolo;
    public static double addYCoordinates;
    public static boolean isCitizen;
    public static HashMap<String, Boolean> mobTypes;

    public static HashMap<String, Boolean> supportedPlugins;
    public static int cooldown;
    public static boolean sneaking;

    public Settings(EDIMain plugin) {
        plugin.reloadConfig();
        damageMessageHolo = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Damage-Message"));
        regenerationMessageHolo = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Regeneration-Message"));
        addYCoordinates = plugin.getConfig().getDouble("add-y-coord");
        isCitizen = plugin.getServer().getPluginManager().getPlugin("Citizens") != null;
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("types");
        mobTypes = new HashMap<>();
        for(String key : section.getKeys(false))
            mobTypes.put(key, section.getBoolean(key));
        ConfigurationSection hooks = config.getConfigurationSection("hooks");
        supportedPlugins = new HashMap<>();
        for(String key : hooks.getKeys(false))
            supportedPlugins.put(key, hooks.getBoolean(key));
        cooldown = plugin.getConfig().getInt("cooldown");
        sneaking = plugin.getConfig().getBoolean("sneaking");
    }

}