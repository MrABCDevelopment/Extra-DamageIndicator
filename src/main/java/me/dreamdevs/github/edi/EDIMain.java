package me.dreamdevs.github.edi;

import me.dreamdevs.github.edi.commands.CommandHandler;
import me.dreamdevs.github.edi.listeners.EntityDamageListener;
import me.dreamdevs.github.edi.listeners.EntityRegainHealthListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EDIMain extends JavaPlugin {
    private static EDIMain instance;
    private HologramsHandler hologramsHandler;
    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();

        new Settings(this);

        new CommandHandler(this);
        this.hologramsHandler = new HologramsHandler();

        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new EntityRegainHealthListener(), this);

        if(getConfig().getBoolean("update-checker")) {
                Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> new UpdateChecker(EDIMain.getInstance(), 101443).getVersion(version -> {
                if (getDescription().getVersion().equals(version)) {
                    Bukkit.getConsoleSender().sendMessage("");
                    Bukkit.getConsoleSender().sendMessage("&aYour version is up to date!");
                    Bukkit.getConsoleSender().sendMessage("&aYour version: " + getDescription().getVersion());
                    Bukkit.getConsoleSender().sendMessage("");
                } else {
                    Bukkit.getConsoleSender().sendMessage("");
                    Bukkit.getConsoleSender().sendMessage("&aThere is new Extra-DamageIndicator version!");
                    Bukkit.getConsoleSender().sendMessage("&aYour version: " + getDescription().getVersion());
                    Bukkit.getConsoleSender().sendMessage("&aNew version: " + version);
                    Bukkit.getConsoleSender().sendMessage("");
                }
            }), 10L, 20 * 600);
        }
    }

    public static EDIMain getInstance() {
        return instance;
    }

    public HologramsHandler getHologramsHandler() {
        return hologramsHandler;
    }
}