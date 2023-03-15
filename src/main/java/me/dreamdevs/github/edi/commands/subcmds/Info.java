package me.dreamdevs.github.edi.commands.subcmds;

import me.dreamdevs.github.edi.ColourUtil;
import me.dreamdevs.github.edi.Settings;
import me.dreamdevs.github.edi.commands.ArgumentCommand;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class Info implements ArgumentCommand {

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        commandSender.sendMessage(ColourUtil.colorize("&6Settings:"));
        commandSender.sendMessage(ColourUtil.colorize("&6» Adding Y Coord: "+ Settings.addYCoordinates));
        commandSender.sendMessage(ColourUtil.colorize("&6» Citizens enabled: "+ Settings.isCitizen));
        commandSender.sendMessage(ColourUtil.colorize("&6» Cooldown: "+ Settings.cooldown+"s"));
        commandSender.sendMessage(ColourUtil.colorize("&6» Sneaking: "+ Settings.sneaking));
        commandSender.sendMessage(ColourUtil.colorize("&6» Mob Types: "));
        for(Map.Entry<String, Boolean> maps : Settings.mobTypes.entrySet())
            commandSender.sendMessage(ColourUtil.colorize("&6» "+maps.getKey()+": "+maps.getValue()));
        commandSender.sendMessage(ColourUtil.colorize("&6» Supported Plugins: "));
        for(Map.Entry<String, Boolean> maps : Settings.supportedPlugins.entrySet())
            commandSender.sendMessage(ColourUtil.colorize("&6» "+maps.getKey()+": "+maps.getValue()));
        return true;
    }

    @Override
    public String getHelpText() {
        return "&6/edi info - shows all informations";
    }

    @Override
    public String getPermission() {
        return "edi.command";
    }
}