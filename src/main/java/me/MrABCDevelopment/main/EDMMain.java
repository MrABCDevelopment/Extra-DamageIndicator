package me.MrABCDevelopment.main;

import me.MrABCDevelopment.HologramsHandler;
import me.MrABCDevelopment.commands.EdiReload;
import me.MrABCDevelopment.events.Damage;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EDMMain extends JavaPlugin {
    private static EDMMain plugin;
    private Damage damage;
    private HologramsHandler hologramsHandler;

    public static EDMMain getInstance() {
        return plugin;
    }

    public static boolean isCitizens;

    @Override
    public void onEnable() {
        plugin = this;
        PluginManager pm = Bukkit.getServer().getPluginManager();
        if(pm.getPlugin("HolographicDisplays") == null) {
            //return;
        } else {
            Bukkit.getLogger().info("Plugin HolographicDisplays jest aktywny!");
            Bukkit.getLogger().info("Od teraz Extra-DamageIndicator bedzie z niego korzystał!");
        }
        isCitizens = pm.getPlugin("Citizens") != null;
        this.saveDefaultConfig();
        new EdiReload(this);
        hologramsHandler = new HologramsHandler(this);
        damage = new Damage(this);
    }

    @Override
    public void onDisable() {
        if(HologramsHandler.ArmorStands.isEmpty()) return;
        HologramsHandler.ArmorStands.forEach((entity, l) -> {
            if(l < System.currentTimeMillis()) {
                ((LivingEntity) entity).remove();
                ((LivingEntity) entity).damage(999);
            }
        });
        HologramsHandler.ArmorStands.clear();
    }

    public HologramsHandler getHologramsHandler() {
        return hologramsHandler;
    }
}
