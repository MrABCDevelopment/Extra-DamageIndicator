package me.MrABCDevelopment.commands;

import me.MrABCDevelopment.main.EDMMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EdiReload implements CommandExecutor{

    private final EDMMain plugin;
    public EdiReload(EDMMain plugin) {
        this.plugin = plugin;
        plugin.getCommand("EdiReload").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        EDMMain.getInstance().reloadConfig();
        plugin.getHologramsHandler().Reload();
        plugin.getHologramsHandler().ReloadVariables();
        sender.sendMessage("§aDone!");
        return true;
    }
}
