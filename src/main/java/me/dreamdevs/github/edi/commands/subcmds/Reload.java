package me.dreamdevs.github.edi.commands.subcmds;

import me.dreamdevs.github.edi.ColourUtil;
import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.Settings;
import me.dreamdevs.github.edi.commands.ArgumentCommand;
import org.bukkit.command.CommandSender;

public class Reload implements ArgumentCommand {

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        new Settings(EDIMain.getInstance());
        commandSender.sendMessage(ColourUtil.colorize("&bConfiguration file has reloaded!"));
        return true;
    }

    @Override
    public String getHelpText() {
        return "&6/edi reload - reloads configuration file";
    }

    @Override
    public String getPermission() {
        return "edi.command";
    }
}