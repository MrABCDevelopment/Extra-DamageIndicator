package me.MrABCDevelopment;

import me.MrABCDevelopment.main.EDMMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;

public class HologramsHandler {

    private HologramsAPI hologramsAPI;
    public static HashMap<ArmorStand, Long> ArmorStands;
    public static HashMap<String, Boolean> Types;
    public static String m;
    public static int y;
    public static int c;
    BukkitScheduler scheduler = Bukkit.getScheduler();

    EDMMain plugin;

    public HologramsHandler(EDMMain plugin) {
        this.plugin = plugin;
        try {
            Class<? extends HologramsAPI> hapi = null;
            if(Bukkit.getPluginManager().getPlugin("HolographicDisplays") != null) {
                hapi = Class.forName("me.MrABCDevelopment.holograms.HolographicDisplaysEDI").asSubclass(HologramsAPI.class);
            } else {
                ArmorStands = new HashMap<>();
                hapi = Class.forName("me.MrABCDevelopment.holograms.DefaultHolograms").asSubclass(HologramsAPI.class);
            }
            hologramsAPI = hapi.newInstance();
        } catch (Exception e) {

        }
        loadHolograms(plugin);
    }

    public void loadHolograms(EDMMain plugin) {
        this.plugin = plugin;
        ArmorStands = new HashMap<>();
        Types = new HashMap<>();
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("types");
        for(String key : section.getKeys(false))
            Types.put(key, section.getBoolean(key));
        c = config.getInt("cooldown");
        m = ChatColor.translateAlternateColorCodes('&', config.getString("message"));
        y = config.getInt("y");
        hologramsAPI.removeHologram();
    }

    public void Reload() {
        ArmorStands.forEach((entity, l) -> {
            if (l < System.currentTimeMillis()) {
                ((LivingEntity) entity).remove();
                ((LivingEntity) entity).damage(999);
            }
        });
        ArmorStands.clear();
        Types.clear();
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("types");
        for(String key : section.getKeys(false))
            Types.put(key, section.getBoolean(key));
    }
    public void ReloadVariables() {
        FileConfiguration config = plugin.getConfig();
        c = config.getInt("cooldown");
        m = ChatColor.translateAlternateColorCodes('&', config.getString("message"));
        y = config.getInt("y");
        scheduler.cancelTasks(plugin);
        hologramsAPI.removeHologram();
    }

    public HologramsAPI getHologramsAPI() {
        return hologramsAPI;
    }
}