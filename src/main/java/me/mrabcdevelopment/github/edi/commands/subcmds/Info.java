package me.mrabcdevelopment.github.edi.commands.subcmds;

import me.mrabcdevelopment.github.edi.Settings;
import me.mrabcdevelopment.github.edi.commands.ArgumentCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class Info implements ArgumentCommand {

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        commandSender.sendMessage(ChatColor.GOLD+"All settings:");
        commandSender.sendMessage(ChatColor.GOLD+"» Adding Y Coord: "+ Settings.addYCoordinates);
        commandSender.sendMessage(ChatColor.GOLD+"» Citizens enabled: "+ Settings.isCitizen);
        commandSender.sendMessage(ChatColor.GOLD+"» Cooldown: "+ Settings.cooldown+"s");
        commandSender.sendMessage(ChatColor.GOLD+"» Sneaking: "+ Settings.sneaking);
        commandSender.sendMessage(ChatColor.GOLD+"» Mob Types: ");
        for(Map.Entry<String, Boolean> maps : Settings.mobTypes.entrySet())
            commandSender.sendMessage(ChatColor.GOLD+"» "+maps.getKey()+": "+maps.getValue());
        commandSender.sendMessage(ChatColor.GOLD+"» Supported Plugins: ");
        for(Map.Entry<String, Boolean> maps : Settings.supportedPlugins.entrySet())
            commandSender.sendMessage(ChatColor.GOLD+"» "+maps.getKey()+": "+maps.getValue());
        return true;
    }

    @Override
    public String getHelpText() {
        return ChatColor.GOLD+ "/edi info - shows all informations";
    }

    @Override
    public String getPermission() {
        return "edi.command";
    }
}