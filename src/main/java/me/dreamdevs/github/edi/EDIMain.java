package me.dreamdevs.github.edi;

import lombok.Getter;
import me.dreamdevs.github.edi.commands.CommandHandler;
import me.dreamdevs.github.edi.listeners.EntityHealthChangeListeners;
import me.dreamdevs.github.edi.listeners.InventoryListener;
import me.dreamdevs.github.edi.managers.IndicatorManager;
import me.dreamdevs.github.edi.managers.SettingsManager;
import me.dreamdevs.github.edi.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class EDIMain extends JavaPlugin {

    private @Getter static EDIMain instance;
    private SettingsManager settingsManager;
    private IndicatorManager indicatorManager;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();

        this.settingsManager = new SettingsManager(this);
        this.indicatorManager = new IndicatorManager();

        new CommandHandler(this);

        getServer().getPluginManager().registerEvents(new EntityHealthChangeListeners(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        new Metrics(this, 19021);

        if(getSettingsManager().getValueAsBoolean("update-checker")) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> new UpdateChecker(EDIMain.getInstance(), 101443).getVersion(version -> {
                if (getDescription().getVersion().equals(version)) {
                    Util.sendPluginMessage("");
                    Util.sendPluginMessage("&aYour version is up to date!");
                    Util.sendPluginMessage("&aYour version: " + getDescription().getVersion());
                    Util.sendPluginMessage("");
                } else {
                    Util.sendPluginMessage("");
                    Util.sendPluginMessage("&aThere is new Extra-DamageIndicator version!");
                    Util.sendPluginMessage("&aYour version: " + getDescription().getVersion());
                    Util.sendPluginMessage("&aNew version: " + version);
                    Util.sendPluginMessage("");
                }
            }), 10L, 20 * 600);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getWorlds().forEach(world -> world.getEntities().stream()
                .filter(ArmorStand.class::isInstance)
                .filter(entity -> entity.hasMetadata("holo"))
                .forEach(Entity::remove));
    }

    public static EDIMain getInstance() {
        return instance;
    }

}