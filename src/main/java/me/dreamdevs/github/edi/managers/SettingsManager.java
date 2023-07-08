package me.dreamdevs.github.edi.managers;

import lombok.Getter;
import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.utils.ColourUtil;
import me.dreamdevs.github.edi.utils.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class SettingsManager {

    private final Map<String, Object> settings;
    private final Map<String, String> messages;

    public SettingsManager(EDIMain plugin) {
        this.settings = new HashMap<>();
        this.messages = new HashMap<>();

        load(plugin);
    }

    public void load(EDIMain plugin) {
        plugin.reloadConfig();
        messages.clear();
        settings.clear();

        settings.put("citizens-enabled", plugin.getServer().getPluginManager().getPlugin("Citizens") != null);

        FileConfiguration config = plugin.getConfig();

        ConfigurationSection section = config.getConfigurationSection("messages");
        section.getKeys(false).forEach(s -> messages.put(s, ColourUtil.colorize(Objects.requireNonNull(section.getString(s)))));

        ConfigurationSection sectionSettings = config.getConfigurationSection("settings");
        sectionSettings.getKeys(false).forEach(s -> settings.put(s, sectionSettings.get(s)));

        Util.sendPluginMessage(messages.get("loaded-configuration").replaceAll("%AMOUNT_MESSAGES%", Integer.toString(messages.size())).replaceAll("%AMOUNT_SETTINGS%", Integer.toString(settings.size())));
    }

    public String getMessage(String key) {
        return messages.get(key);
    }

    public Object getValueAsObject(String key) {
        return settings.get(key);
    }

    public String getValueAsString(String key) {
        return (String) settings.get(key);
    }

    public int getValueAsInt(String key) {
        return (int) settings.get(key);
    }

    public double getValueAsDouble(String key) {
        return (double) settings.get(key);
    }

    public boolean getValueAsBoolean(String key) {
        return (boolean) settings.get(key);
    }

}