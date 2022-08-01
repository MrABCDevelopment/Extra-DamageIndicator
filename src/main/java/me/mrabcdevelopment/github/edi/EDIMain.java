package me.mrabcdevelopment.github.edi;

import me.mrabcdevelopment.github.edi.commands.CommandHandler;
import me.mrabcdevelopment.github.edi.listeners.EntityDamageListener;
import me.mrabcdevelopment.github.edi.listeners.EntityRegainHealthListener;
import org.bukkit.plugin.java.JavaPlugin;

public class EDIMain extends JavaPlugin {
    private static EDIMain instance;
    private HologramsHandler hologramsHandler;
    private CommandHandler commandHandler;
    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();

        new Settings(this);

        this.commandHandler = new CommandHandler(this);
        this.hologramsHandler = new HologramsHandler(this);

        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new EntityRegainHealthListener(), this);
    }

    public static EDIMain getInstance() {
        return instance;
    }

    public HologramsHandler getHologramsHandler() {
        return hologramsHandler;
    }
}